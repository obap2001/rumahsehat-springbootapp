package tk.apap.rumahsehat.controller;

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

    @PostMapping(value = "/resep/add", params = {"deleteRowObat"})
    private String deleteRowObatMultiple(@ModelAttribute ResepModel resep, @RequestParam("deleteRowObat") Integer row, Model model) {
        final Integer rowId = Integer.valueOf(row);
        resepService.getListObat().remove(rowId.intValue());

        List<ObatModel> listObat = obatService.getListObat();

        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);

        return "resep/form-add-resep";
    }

    @PostMapping(value = "/resep/add", params = {"save"})
    public String addResepSubmit(@ModelAttribute ResepModel resep, Model model) {
        if (resepService.getListObat() == null) {
            resep.setListJumlah(new ArrayList<>());
        }

//        List<ObatModel> listObat = resepService.getListObat();
        LocalDateTime sekarang = LocalDateTime.now();
//        ApotekerModel apoteker = new ApotekerModel();

        if (resep.getApoteker() == null) {
            resep.setApoteker(new ApotekerModel());
        }

        if (resep.getAppointment() == null) {
            AppointmentModel appointment = new AppointmentModel();
//            appointment.setResep(resep);
            appointment.setIsDone(false);
            appointment.setWaktuAwal(sekarang);
            appointment.setDokter(new DokterModel());
            appointment.setPasien(new PasienModel());
            appointment.setTagihan(new TagihanModel());
            resep.setAppointment(appointment);
        }
        resep.setIsDone(false);
        resep.setCreatedAt(sekarang);
//        resep.setApoteker(apoteker);
        resepService.addResep(resep);

//        for (int i = 0; i < apotekerService.getListApoteker().size(); i++) {
//            if (apotekerService.getListApoteker().get(i) == apoteker) {
//                apotekerService.getListApoteker().get(i).getListResep().add(resep);
//            }
//            else {
//                apoteker.getListResep().add(resep);
//            }
//        }

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
