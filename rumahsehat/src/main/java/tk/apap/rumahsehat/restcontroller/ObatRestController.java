package tk.apap.rumahsehat.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.apap.rumahsehat.model.ObatModel;
import tk.apap.rumahsehat.service.ObatRestService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ObatRestController {
    @Autowired
    private ObatRestService obatRestService;

    //retrieve all
    @GetMapping("/data-obat")
    private List<ObatModel> retrieveListObat(){
        return obatRestService.retrieveListObat();
    }
}
