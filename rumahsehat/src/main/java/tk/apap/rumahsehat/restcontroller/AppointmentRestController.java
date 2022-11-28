package tk.apap.rumahsehat.restcontroller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import tk.apap.rumahsehat.model.AppointmentModel;
import tk.apap.rumahsehat.model.UserModel;
import tk.apap.rumahsehat.service.AppointmentService;
import tk.apap.rumahsehat.service.UserService;


@Controller
@RequestMapping("/appointment")
public class AppointmentRestController {
    @Qualifier("appointmentServiceImpl")
    @Autowired
    private AppointmentService appointmentService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @GetMapping(value = "/view-all")
    public String viewListAppointment(Model model, HttpServletRequest servreq){
//        String role = userService.getUserByUsername(servreq.getRemoteUser()).getRole();
//        if (role.equals("dokter") || role.equals("admin")) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        UserModel userModel = userService.getUserByUsername(username);
        model.addAttribute("isDokter", userModel.getRole().equals("dokter"));
        model.addAttribute("role", userModel.getRole());
        model.addAttribute("listAppointment", appointmentService.getListAppointment());
        return "appointment/viewall-appointment";
//        }else
//            return "redirect:/";
    }
}
