package tk.apap.rumahsehat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.apap.rumahsehat.model.AppointmentModel;
import tk.apap.rumahsehat.service.AppointmentService;
import tk.apap.rumahsehat.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AppointmentController {
    @Qualifier("appointmentServiceImpl")
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @GetMapping("/appointment/add")
    public String addAppointmentFormPage(Model model) {
        AppointmentModel appointment = new AppointmentModel();
        model.addAttribute("appointment", appointment);
        return "appointment/form-add-appointment";
    }


    @PostMapping(value="/appointment/add", params={"save"})
    public String addAppointmentSubmit(@ModelAttribute AppointmentModel appointment, Model model) {
        return "add-appointment";
    }


    @GetMapping(value = "/appointment/viewall")
    public String listAppointment(Model model, HttpServletRequest servreq){
        String role = userService.getUserByUsername(servreq.getRemoteUser()).getRole();
        model.addAttribute("role", role);
        model.addAttribute("listObat", appointmentService.getListAppointment());
        return "appointment/viewall-appointment";
    }

    @GetMapping("/appointment/view")
    public String viewDetailAppointment(@RequestParam(value = "code") String code, Model model) {
        return "appointment/appointment-details";
    }



}
