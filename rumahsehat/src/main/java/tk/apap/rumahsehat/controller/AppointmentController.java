package tk.apap.rumahsehat.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
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
import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;


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
        String role = userService.getUserByUsername(servreq.getRemoteUser()).getRole();
        UserModel userModel = userService.getUserByUsername(servreq.getRemoteUser());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();

        AppointmentModel appointment = new AppointmentModel();
        List<DokterModel> listDokter = dokterService.getListDokter();
        List<DokterModel> listDokterNew = new ArrayList<>();
        List<UserModel> listUser = userService.getListUser();

        //appointment.setPasien(userModel);
        model.addAttribute("appointment", appointment);
        model.addAttribute("listDokter", listDokter);
        model.addAttribute("User", userModel);
        model.addAttribute("Dokter", appointment.getDokter());

        return "appointment/form-add-appointment";
    }

    @PostMapping(value = "/appointment/add", params = {"save"})
    public String addAppointmentSubmit(@ModelAttribute AppointmentModel appointment, Model model, HttpServletRequest servreq) {
        //DokterModel dokter = new DokterModel();
        String role = userService.getUserByUsername(servreq.getRemoteUser()).getRole();
        UserModel userModel = userService.getUserByUsername(servreq.getRemoteUser());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();

        PasienModel pasien = pasienService.getPasienByUsername(username);
        appointment.setIsDone(false);
        appointment.setPasien(pasien);

        List<AppointmentModel> listAppointment = appointmentService.getListAppointment();
        listAppointment.add(appointment);

        //List<AppointmentModel> listAppointmentDokter = dokterService.getListAppointment();

        appointmentService.addAppointment(appointment);


        return "appointment/add-appointment";
    }

    @PostMapping("/appointment/add")
    public String addAppointmentSubmitPage(@ModelAttribute AppointmentModel appointment, Model model) {
        appointmentService.addAppointment(appointment);
        model.addAttribute("dokter", appointment.getDokter());
        return "appointment/add-appointment";
    }

    @GetMapping("/appointment/viewall")
    public String viewAllAppointment(Model model,HttpServletRequest servreq) {
        String role = userService.getUserByUsername(servreq.getRemoteUser()).getRole();
        UserModel userModel = userService.getUserByUsername(servreq.getRemoteUser());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();

        List<DokterModel> listDokter = dokterService.getListDokter();

        if(role.equals("dokter")){
            DokterModel dokter = dokterService.getDokterByUsername(servreq.getRemoteUser());
            List<AppointmentModel> listAppointmentDokter = dokter.getListAppointment();
            model.addAttribute("listAppointmentDokter", listAppointmentDokter);
        } else if (role.equals("pasien")){
            PasienModel pasien = pasienService.getPasienByUsername(servreq.getRemoteUser());
            List<AppointmentModel> listAppointmentPasien = pasien.getListAppointment();
            model.addAttribute("listAppointmentPasien", listAppointmentPasien);
        }

        model.addAttribute("role", role);
        model.addAttribute("listAppointment", appointmentService.getListAppointment());
        model.addAttribute("listDokter", listDokter);

        return "appointment/viewall-appointment";
    }

    @GetMapping("/appointment/details/{kode}")
    public String viewDetailAppointment(@PathVariable String kode, Model model) {
        AppointmentModel appointment = appointmentService.getAppointmentById(kode);
        Boolean adaResep = false;
        if (!appointment.getResep().equals(null)) {
            adaResep = true;
        }
        model.addAttribute("appointment", appointment);
        model.addAttribute("adaResep", adaResep);
        model.addAttribute("idResep", appointment.getResep().getId());
        return "appointment/appointment-details";
    }


}