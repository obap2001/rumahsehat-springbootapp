package tk.apap.rumahsehat.controller;

import tk.apap.rumahsehat.model.*;
import tk.apap.rumahsehat.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ResepController {

    @Qualifier("resepServiceImpl")
    @Autowired
    private ResepService resepService;

//    @Qualifier("obatServiceImpl")
//    @Autowired
//    private ObatService obatService;

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

//    @GetMapping("/resep/add")
//    public String addResepFormPage(Model model) {
//        ResepModel resep = new ResepModel();
//        List<ObatModel> listObat = obatService.getListObat();
//        List<ObatModel> listObatNew = new ArrayList<>();
//
//
//    }

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
