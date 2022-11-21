package tk.apap.rumahsehat.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import tk.apap.rumahsehat.setting.Setting;
import tk.apap.rumahsehat.model.AdminModel;
import tk.apap.rumahsehat.model.UserModel;
import tk.apap.rumahsehat.security.xml.Attributes;
import tk.apap.rumahsehat.security.xml.ServiceResponse;
import tk.apap.rumahsehat.service.AdminService;
import tk.apap.rumahsehat.service.UserService;

@Controller
public class PageController {
    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;
    
    @Autowired
    ServerProperties serverProperties;
    
    @RequestMapping("/")
    private String home(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        UserModel userModel = userService.getUserByUsername(username);

        model.addAttribute("user", userModel);
        return "home";
    }

    @RequestMapping("/login")
    private String login(Model model) {
    //   model.addAttribute("port", serverProperties.getPort());
      return "login";
    }
  
    private WebClient webClient = WebClient.builder().build();
  
    @GetMapping("/validate-ticket")
    public ModelAndView adminLoginSSO(
        @RequestParam(value = "ticket", required = false) String ticket,
        HttpServletRequest request
    ) {
      ServiceResponse serviceResponse = this.webClient.get().uri(
        String.format(
          Setting.SERVER_VALIDATE_TICKET,
          ticket,
          Setting.CLIENT_LOGIN
        )
      ).retrieve().bodyToMono(ServiceResponse.class).block();
  
      Attributes attributes = serviceResponse.getAuthenticationSuccess().getAttributes();
      String username = serviceResponse.getAuthenticationSuccess().getUser();

      AdminModel admin = adminService.getUserByUsername(username);
  
      if (admin == null) {
        admin = new AdminModel();
        admin.setEmail(username + "@ui.ac.id");
        admin.setNama(attributes.getNama());
        admin.setPassword("rumahsehat");
        admin.setUsername(username);
        admin.setIsSso(true);
        admin.setRole("admin");
        adminService.addAdmin(admin);
      }
  
      Authentication authentication = new UsernamePasswordAuthenticationToken(username, "rumahsehat");
      
      SecurityContext securityContext = SecurityContextHolder.getContext();
      securityContext.setAuthentication(authentication);
  
      HttpSession httpSession = request.getSession(true);
      httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
  
      return new ModelAndView("redirect:/");
    }

    @RequestMapping("/user/viewall")
    private String manajemenUser(Model model) {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      User user = (User) auth.getPrincipal();
      String username = user.getUsername();
      UserModel userModel = userService.getUserByUsername(username);
      if (userModel.getRole().equals("admin")) {
        return "manajemen-user";
      }
        return "home";
    }
  
    @GetMapping(value="/login-sso")
    public ModelAndView loginSSO() {
      return new ModelAndView("redirect:" + Setting.SERVER_LOGIN + Setting.CLIENT_LOGIN);
    }
  
    @GetMapping(value = "/logout-sso")
      public ModelAndView logoutSSO(Principal principal) {
          UserModel user = userService.getUserByUsername(principal.getName());
          if (user.getIsSso() == false){
              return new ModelAndView("redirect:/logout");
          }
          return new ModelAndView("redirect:" + Setting.SERVER_LOGOUT + Setting.CLIENT_LOGOUT);
      }
}
