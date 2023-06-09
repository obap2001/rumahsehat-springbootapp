package tk.apap.rumahsehat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import tk.apap.rumahsehat.model.ApotekerModel;
import tk.apap.rumahsehat.service.ApotekerService;
import tk.apap.rumahsehat.service.UserService;

@Slf4j
@Controller
public class ApotekerController {
  @Qualifier("apotekerServiceImpl")
  @Autowired
  private ApotekerService apotekerService;

  @Qualifier("userServiceImpl")
  @Autowired
  private UserService userService;

  @GetMapping("/apoteker/viewall")
  public String viewAllApoteker(
    Model model
  ){
    model.addAttribute("listApoteker", apotekerService.getListApoteker());
    log.info("mengambil data semua apoteker");
    return "apoteker/viewall-apoteker";
  }

  @GetMapping("/apoteker/add")
  public String addApotekerForm(Model model){
    model.addAttribute("apoteker", new ApotekerModel());
    return "apoteker/form-add-apoteker";
  }

  @PostMapping(value = "/apoteker/add")
  public String addApotekerSubmit(
    @ModelAttribute ApotekerModel apoteker,
    Model model
  ){
    apoteker.setRole("apoteker");
    apoteker.setIsSso(false);
    apotekerService.addApoteker(apoteker);
    model.addAttribute("nama", apoteker.getNama());
    log.info("menambah data apoteker baru");
    return "apoteker/success-add-apoteker";
  }
}


