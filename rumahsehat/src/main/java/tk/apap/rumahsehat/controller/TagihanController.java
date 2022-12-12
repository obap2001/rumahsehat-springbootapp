package tk.apap.rumahsehat.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;

// import tk.apap.rumahsehat.model.PasienModel;
// import tk.apap.rumahsehat.model.UserModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tk.apap.rumahsehat.model.*;
import tk.apap.rumahsehat.service.*;

@Controller
@RequestMapping("/tagihan")
public class TagihanController {
  @Qualifier("tagihanServiceImpl")
  @Autowired
  private TagihanService tagihanService;

  @RequestMapping(value = "/chart", method = RequestMethod.GET)
  public String homeChart(Model model) {
      return "charts/home-charts";
  }

  @RequestMapping(value = "/chart/default", method = RequestMethod.GET)
  public String getDefaultChart(Model model) {
      //String response = restTemplate.getForObject("https://api.open-meteo.com/v1/forecast?latitude=-6.1862&longitude=106.8063&daily=temperature_2m_max&timezone=Asia/Bangkok&past_days=1", String.class);
      //log.info(response);

      // WeatherDTO response = restTemplate.getForObject("https://api.open-meteo.com/v1/forecast?latitude=-6.1862&longitude=106.8063&daily=temperature_2m_max&timezone=Asia/Bangkok&past_days=1",
      //         WeatherDTO.class);
      // log.info(response.toString());

      Month bulanIni = LocalDate.now().getMonth();
      int tahunIni = LocalDate.now().getYear();
      HashMap<Integer, Integer> data = tagihanService.mapTanggaltoJumlahTagihanByBulanIni(bulanIni);
      model.addAttribute("data", data);
      model.addAttribute("bulanIni", bulanIni);
      model.addAttribute("tahunIni", tahunIni);
      System.out.println(data);
      return "charts/default";
  }
}


