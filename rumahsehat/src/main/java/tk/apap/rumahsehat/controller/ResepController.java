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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ResepController {

    @Qualifier("appointmentServiceImpl")
    @Autowired
    private AppointmentService appointmentService;

    @Qualifier("resepServiceImpl")
    @Autowired
    private ResepService resepService;

    @Qualifier("obatServiceImpl")
    @Autowired
    private ObatService obatService;

    @Qualifier("apotekerServiceImpl")
    @Autowired
    private ApotekerService apotekerService;

    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

    @Qualifier("adminServiceImpl")
    @Autowired
    private AdminService adminService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;


    @GetMapping("/resep/add")
    public String addResepFormPage(Model model) {
        ResepModel resep = new ResepModel();
        List<ObatModel> listObat = resepService.getListObat();
        List<JumlahModel> listJumlah = new ArrayList<>();

        resep.setListJumlah(listJumlah);
        resep.getListJumlah().add(new JumlahModel());
//        apoteker.getListResep().add(resep);

        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);

        return "resep/form-add-resep";
    }

//    @GetMapping("/resep/add/{kode}")
//    public String addResepFormPage(@PathVariable String kode, Model model) {
//        ResepModel resep = new ResepModel();
//        List<JumlahModel> listJumlah = new ArrayList<>();
//        List<ObatModel> listObat = resepService.getListObat();
//        AppointmentModel appointment = appointmentService.getAppointmentByKode(kode);
//
//        if (appointment.getIsDone().equals(false)) {
//            resep.setListJumlah(listJumlah);
//            resep.getListJumlah().add(new JumlahModel());
//            resep.setAppointment(appointment);
//
//            model.addAttribute("kodeAppointment", kode);
//            model.addAttribute("resep", resep);
//            model.addAttribute("listObat", listObat);
//
//            return "resep/form-add-resep";
//        }
//
//        else {
//            return "resep/appointment-done";
//        }
//    }

    @PostMapping(value = "/resep/add", params = {"addRowObat"})
    private String addRowObatMultiple(@ModelAttribute ResepModel resep, Model model) {
        if (resepService.getListObat() == null || resepService.getListObat().size() == 0) {
            resep.setListJumlah(new ArrayList<>());
        }

        resep.getListJumlah().add(new JumlahModel());
        List<ObatModel> listObat = obatService.getListObat();

        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);

        return "resep/form-add-resep";
    }

//    @PostMapping(value = "/resep/add/{kode}", params = {"addRowObat"})
//    private String addRowObatMultiple(@PathVariable String kode, @ModelAttribute ResepModel resep, Model model) {
//        if (resepService.getListObat() == null || resepService.getListObat().size() == 0) {
//            resep.setListJumlah(new ArrayList<>());
//        }
//
//        resep.getListJumlah().add(new JumlahModel());
//        List<ObatModel> listObat = obatService.getListObat();
//
//        model.addAttribute("kodeAppointment", kode);
//        model.addAttribute("resep", resep);
//        model.addAttribute("listObat", listObat);
//
//        return "resep/form-add-resep";
//    }

    @PostMapping(value = "/resep/add", params = {"deleteRowObat"})
    private String deleteRowObatMultiple(@ModelAttribute ResepModel resep, @RequestParam("deleteRowObat") Integer row, Model model) {
        final Integer rowId = Integer.valueOf(row);
        resep.getListJumlah().remove(rowId.intValue());

        List<JumlahModel> listJumlah = resep.getListJumlah();

        model.addAttribute("resep", resep);
        model.addAttribute("listJumlah", listJumlah);

        return "resep/form-add-resep";
    }

//    @PostMapping(value = "/resep/add/{kode}", params = {"deleteRowObat"})
//    private String deleteRowObatMultiple(@PathVariable String kode, @ModelAttribute ResepModel resep, @RequestParam("deleteRowObat") Integer row, Model model) {
//        final Integer rowId = Integer.valueOf(row);
//        resep.getListJumlah().remove(rowId.intValue());
//
//        List<JumlahModel> listJumlah = resep.getListJumlah();
//
//        model.addAttribute("kodeAppointment", kode);
//        model.addAttribute("resep", resep);
//        model.addAttribute("listJumlah", listJumlah);
//
//        return "resep/form-add-resep";
//    }

    @PostMapping(value = "/resep/add", params = {"save"})
    public String addResepSubmit(@ModelAttribute ResepModel resep, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        DokterModel dokter = dokterService.getDokterByUsername(username);

        if (resep.getListJumlah() == null) {
            resep.setListJumlah(new ArrayList<>());
        }

        if (resep.getApoteker() == null) {
            resep.setApoteker(apotekerService.getListApoteker().get(0));
        }

        List<AppointmentModel> listAppointment = appointmentService.getListAppointmentByDokter(dokter);
        AppointmentModel appointment = listAppointment.get(listAppointment.size() - 1);
        resep.setAppointment(appointment);
        resep.setIsDone(appointment.getIsDone());
        resep.setCreatedAt(appointment.getWaktuAwal());

//        long idTemp = resepService.getListResep().size();
//        resep.setId(idTemp);
//        resep.setIsDone(false);
//        resep.setCreatedAt(LocalDateTime.now());
//        resep.setApoteker(null);
//        resep.setAppointment(null);

        resepService.addResep(resep);

        model.addAttribute("idResep", resep.getId());

        return "resep/add-resep";
    }

    @PostMapping("/resep/add")
    public String addResepSubmitPage(@ModelAttribute ResepModel resep, Model model) {
        resepService.addResep(resep);

        model.addAttribute("idResep", resep.getId());

        return "resep/add-resep";
    }

    @GetMapping("/resep/viewall")
    public String viewAllResep(Model model) {
        model.addAttribute("listResep", resepService.getListResep());

        return "resep/viewall-resep";
    }

    @GetMapping("/resep/view")
    public String viewDetailResepPage(@RequestParam(value = "id") Long id, Model model) {
        ResepModel resep = resepService.getResepById(id);

        model.addAttribute("resep", resep);

        return "resep/viewdetail-resep";
    }

    @GetMapping("/resep/delete/{id}")
    public String deleteResep(@PathVariable Long id, Model model) {
        ResepModel resep = resepService.getResepById(id);
        if (resep.getIsDone().equals(true)) {
            return "resep/delete-success";
        }
        else {
            return "resep/delete-failed";
        }
    }

}
