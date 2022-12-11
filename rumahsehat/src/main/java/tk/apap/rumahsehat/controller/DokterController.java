package tk.apap.rumahsehat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import tk.apap.rumahsehat.model.DokterModel;
// import tk.apap.rumahsehat.model.UserModel;
import tk.apap.rumahsehat.service.DokterService;
import tk.apap.rumahsehat.service.UserService;

@Slf4j
@Controller
public class DokterController {
  @Qualifier("dokterServiceImpl")
  @Autowired
  private DokterService dokterService;

  @Qualifier("userServiceImpl")
  @Autowired
  private UserService userService;

  @GetMapping("/dokter/viewall")
  public String viewAllDokter(
    Model model
  ){
    model.addAttribute("listDokter", dokterService.getListDokter());
    log.info("Mengambil data semua dokter.");
    return "dokter/viewall-dokter";
  }

  @GetMapping("/dokter/add")
  public String addDokterForm(Model model){
    model.addAttribute("dokter", new DokterModel());
    return "dokter/form-add-dokter";
  }

  @PostMapping(value = "/dokter/add")
  public String addDokterSubmit(
    @ModelAttribute DokterModel dokter,
    Model model
  ){
    dokter.setRole("dokter");
    dokter.setIsSso(false);
    dokterService.addDokter(dokter);
    model.addAttribute("nama", dokter.getNama());
    log.info("Menambah data dokter baru.");
    return "dokter/success-add-dokter";
  }
}


