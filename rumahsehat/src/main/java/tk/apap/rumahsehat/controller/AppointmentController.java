package tk.apap.rumahsehat.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import tk.apap.rumahsehat.model.*;
import tk.apap.rumahsehat.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;
import org.springframework.boot.autoconfigure.web.ServerProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;


@Controller
//@RequestMapping("/appointment")
public class AppointmentController {

    @Qualifier("appointmentServiceImpl")
    @Autowired
    private AppointmentService appointmentService;

    @Qualifier("obatServiceImpl")
    @Autowired
    private ObatService obatService;

    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

    @Qualifier("adminServiceImpl")
    @Autowired
    private AdminService adminService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @GetMapping("/appointment/add")
    public String addAppointmentFormPage(Model model, HttpServletRequest servreq) {
        AppointmentModel appointment = new AppointmentModel();
        List<DokterModel> listDokter = dokterService.getListDokter();
        List<DokterModel> listDokterNew = new ArrayList<>();

        model.addAttribute("appointment", appointment);
        model.addAttribute("listDokter", listDokter);
        model.addAttribute("listDokterNew", listDokterNew);

        return "appointment/form-add-appointment";
    }

    @PostMapping(value = "/appointment/add", params = {"save"})
    public String addAppointmentSubmit(@ModelAttribute AppointmentModel appointment, Model model, HttpServletRequest servreq) {
        //DokterModel dokter = new DokterModel();
        List<AppointmentModel> listAppointment = new ArrayList<>();
        String role = userService.getUserByUsername(servreq.getRemoteUser()).getRole();
        UserModel userModel = userService.getUserByUsername(servreq.getRemoteUser());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();

        PasienModel pasien = pasienService.getPasienByUsername(username);
        appointment.setIsDone(false);
        appointment.setPasien(pasien);

        listAppointment.add(appointment);
        appointmentService.addAppointment(appointment);

        model.addAttribute("dokter", appointment.getDokter());

        return "appointment/add-appointment";
    }

    @PostMapping("/appointment/add")
    public String addAppointmentSubmitPage(@ModelAttribute AppointmentModel appointment, Model model) {
        appointmentService.addAppointment(appointment);
        model.addAttribute("dokter", appointment.getDokter());
        return "appointment/add-appointment";
    }

    @GetMapping("/appointment/viewall")
    public String viewAllAppointment(Model model) {
        model.addAttribute("listAppointment", appointmentService.getListAppointment());

        return "appointment/viewall-appointment";
    }



}