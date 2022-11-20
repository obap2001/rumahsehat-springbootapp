package tk.apap.rumahsehat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tk.apap.rumahsehat.model.ObatModel;
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
//        if (role.equals("apoteker") || role.equals("admin")) {
            model.addAttribute("role", role);
            model.addAttribute("listObat", obatService.getListObat());
            return "obat/viewall-obat";
//        }else
//            return "redirect:/";
    }

    @GetMapping("/{id}/ubah-stok")
    public String updateObatFormPage(@PathVariable( value = "id") String id, Model model) {
        ObatModel obat = obatService.getObatById(id);
        model.addAttribute("obat", obat);
        return "obat/form-update-stok-obat";
    }

    @PostMapping("/ubah-stok")
    public String updateObatSubmitPage(@ModelAttribute ObatModel obat, Model model) {
        ObatModel updatedObat= obatService.updateObat(obat);
        model.addAttribute("obat", updatedObat);
        return "obat/update-obat";
    }
}
