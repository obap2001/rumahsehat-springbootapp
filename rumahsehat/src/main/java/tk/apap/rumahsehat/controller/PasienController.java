package tk.apap.rumahsehat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;

// import tk.apap.rumahsehat.model.PasienModel;
// import tk.apap.rumahsehat.model.UserModel;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import tk.apap.rumahsehat.model.PasienModel;
import tk.apap.rumahsehat.service.PasienService;
import tk.apap.rumahsehat.service.UserService;


@Controller
public class PasienController {
  @Qualifier("pasienServiceImpl")
  @Autowired
  private PasienService pasienService;

  @Qualifier("userServiceImpl")
  @Autowired
  private UserService userService;

  @GetMapping("/pasien/viewall")
  public String viewAllPasien(
    Model model
  ){
    model.addAttribute("listPasien", pasienService.getListPasien());
    return "pasien/viewall-pasien";
  }

  @GetMapping("/pasien/add")
   public String addPasienForm(Model model){
     model.addAttribute("pasien", new PasienModel());
     return "pasien/form-add-pasien";
  }

  @PostMapping(value = "/pasien/add")
   public String addPasienSubmit(
     @ModelAttribute PasienModel pasien,
     Model model
  ){
     pasien.setRole("pasien");
     pasien.setIsSso(false);
     pasienService.addPasien(pasien);
     model.addAttribute("nama", pasien.getNama());
     return "pasien/success-add-pasien";
  }
}


