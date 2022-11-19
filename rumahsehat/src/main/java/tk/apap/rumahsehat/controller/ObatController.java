package tk.apap.rumahsehat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.apap.rumahsehat.service.ObatService;
import tk.apap.rumahsehat.service.UserService;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/obat")
public class ObatController {
    @Qualifier("obatServiceImpl")
    @Autowired
    private ObatService obatService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @GetMapping(value = "/viewall")
    public String viewAllObat(Model model, HttpServletRequest servreq){
        String role = userService.getUserByUsername(servreq.getRemoteUser()).getRole();
//        if (role.equals("Apoteker") || role.equals("Admin")) {
            model.addAttribute("role", role);
            model.addAttribute("listObat", obatService.getListObat());
            return "obat/viewall-obat";
//        }else
//            return "redirect:/";
    }
}
