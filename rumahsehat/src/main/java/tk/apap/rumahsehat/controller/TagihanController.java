package tk.apap.rumahsehat.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tk.apap.rumahsehat.model.*;
import tk.apap.rumahsehat.service.*;

@Controller
@RequestMapping("/tagihan")
public class TagihanController {
  @Qualifier("tagihanServiceImpl")
  @Autowired
  private TagihanService tagihanService;

  @Qualifier("userServiceImpl")
  @Autowired
  private UserService userService;


  @GetMapping("/chart")
  public String homeChart(Model model) {
      return "charts/home-charts";
  }

  @GetMapping("/chart/default")
  public String getDefaultChart(Model model) {


      Month bulanIni = LocalDate.now().getMonth();
      int tahunIni = LocalDate.now().getYear();
      HashMap<Integer, Integer> data = tagihanService.mapTanggaltoJumlahTagihanByBulanIni(bulanIni);
      model.addAttribute("data", data);
      model.addAttribute("bulanIni", bulanIni);
      model.addAttribute("tahunIni", tahunIni);
      return "charts/default";
  }

    @GetMapping("/chart/line/monthly")
    public String getLineChartMonthly(Model model, HttpServletRequest servreq) {
        String role = userService.getUserByUsername(servreq.getRemoteUser()).getRole();

        if (role.equals("admin")) {
            Month bulan = LocalDate.now().getMonth();
            int tahun = LocalDate.now().getYear();
            HashMap<Integer, Integer> data = tagihanService.mapTanggaltoJumlahTagihanByBulanIni(bulan);

            model.addAttribute("data", data);
            model.addAttribute("bulan", bulan);
            model.addAttribute("tahun", tahun);
        }

        return "error/404";
    }

    @GetMapping("/chart/line/anually")
    public String getLineChartAnnually(Model model, HttpServletRequest servreq) {
        String role = userService.getUserByUsername(servreq.getRemoteUser()).getRole();

        if (role.equals("admin")) {
            Month bulan = LocalDate.now().getMonth();
            int tahun = LocalDate.now().getYear();
            HashMap<Integer, Integer> data = tagihanService.mapBulanToJumlahTagihanByTahun(tahun);

            model.addAttribute("data", data);
            model.addAttribute("bulan", bulan);
            model.addAttribute("tahun", tahun);
        }

        return "error/404";
    }
}


