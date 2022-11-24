package tk.apap.rumahsehat.controller;
import javax.servlet.http.HttpServletRequest;

import tk.apap.rumahsehat.model.*;
import tk.apap.rumahsehat.service.*;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import tk.apap.rumahsehat.model.ObatModel;
import tk.apap.rumahsehat.model.UserModel;
import tk.apap.rumahsehat.model.DokterModel;
import tk.apap.rumahsehat.service.ObatService;
import tk.apap.rumahsehat.service.UserServiceImpl;
import tk.apap.rumahsehat.service.DokterServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppointmentController {

    @Qualifier("appointmentServiceImpl")
    @Autowired
    private AppointmentService appointmentService;

    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @Qualifier("adminServiceImpl")
    @Autowired
    private AdminService adminService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @GetMapping("/appointment/add")
    public String addAppointmentFormPage(Model model) {
        AppointmentModel appointment = new AppointmentModel();
        List<DokterModel> listDokter = dokterService.getListDokter();
        appointment.setIsDone(false);

        model.addAttribute("appointment", appointment);
        model.addAttribute("listDokter", listDokter);

        return "appointment/form-add-appointment";
    }


    @PostMapping(value = "/appointment/add", params = {"save"})
    public String addAppointmentSubmit(@ModelAttribute AppointmentModel appointment, Model model) {
        appointmentService.addAppointment(appointment);
        model.addAttribute("kode", appointment.getKode());
        return "resep/add-appointment";
    }

    @PostMapping("/appointment/add")
    public String addAppointmentSubmitPage(@ModelAttribute AppointmentModel appointment, Model model) {
        appointmentService.addAppointment(appointment);
        model.addAttribute("kode", appointment.getKode());
        return "resep/add-appointment";
    }

    @GetMapping(value = "appointment/viewall")
    public String viewAllAppointment(Model model, HttpServletRequest servreq){
        String role = userService.getUserByUsername(servreq.getRemoteUser()).getRole();
        //String id = userService.getUserById()
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        List<UserModel> listUser = userService.getListUser();
        if (role.equals("Admin")||role.equals("Dokter")||role.equals("Pasien")) {
            model.addAttribute("role", role);
            model.addAttribute("listAppointment", appointmentService.getListAppointment());
            model.addAttribute("listAppointmentDokter", dokterService.getListAppointment());
            model.addAttribute("listAppointmentPasien", pasienService.getListAppointment());
            return "appointment/viewall-appointment";
        }
        return "redirect:/";
    }

    @GetMapping("/appointment/view")
    public String viewDetailAppointmentPage(@RequestParam(value = "kode") String kode, Model model) {
        AppointmentModel appointment = appointmentService.getAppointmentByKode(kode);
        model.addAttribute("appointment", appointment);

        return "appointment/viewdetail-appointment";
    }


}
