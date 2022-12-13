package tk.apap.rumahsehat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import tk.apap.rumahsehat.model.*;
import tk.apap.rumahsehat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
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
        UserModel userModel = userService.getUserByUsername(servreq.getRemoteUser());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        AppointmentModel appointment = new AppointmentModel();
        List<DokterModel> listDokter = dokterService.getListDokter();

        model.addAttribute("appointment", appointment);
        model.addAttribute("listDokter", listDokter);
        model.addAttribute("User", userModel);
        model.addAttribute("Dokter", appointment.getDokter());

        return "appointment/form-add-appointment";
    }

    @PostMapping(value = "/appointment/add", params = {"save"})
    public String addAppointmentSubmit(@ModelAttribute AppointmentModel appointment, Model model, HttpServletRequest servreq) {
        UserModel userModel = userService.getUserByUsername(servreq.getRemoteUser());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();

        PasienModel pasien = pasienService.getPasienByUsername(username);
        appointment.setIsDone(false);
        appointment.setPasien(pasien);

        List<AppointmentModel> listAppointment = appointmentService.getListAppointment();
        listAppointment.add(appointment);
        appointmentService.addAppointment(appointment);

        log.info("Berhasil membuat appointment baru.");
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
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
        model.addAttribute("appointment", appointment);
        log.info("mengambil data appointment terdaftar.");
        return "appointment/appointment-details";
    }

    @GetMapping("/appointment/finish/{kode}")
    public String finishAppointment(@PathVariable String kode, Model model) {
        AppointmentModel appointment = appointmentService.getAppointmentById(kode);
        appointment.setIsDone(true);
        model.addAttribute("appointment", appointment);
        log.info("mengambil data appointment terdaftar.");
        return "appointment/appointment-details";
    }


}