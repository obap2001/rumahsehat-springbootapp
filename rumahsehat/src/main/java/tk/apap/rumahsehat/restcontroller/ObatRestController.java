package tk.apap.rumahsehat.restcontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.apap.rumahsehat.model.ObatModel;
import tk.apap.rumahsehat.service.ObatRestService;

import java.util.List;

@Slf4j
@CrossOrigin("https://apap-078.cs.ui.ac.id/")
@RestController
@RequestMapping("/obat/api")
public class ObatRestController {
    @Autowired
    private ObatRestService obatRestService;

    //retrieve all
    @GetMapping("/data-obat")
    public ResponseEntity getDataObat() {
        log.info("api mengambil data semua obat");
        ResponseEntity responseEntity = null;
        try {
            List<ObatModel> dtoList = obatRestService.retrieveListObat();
            responseEntity = ResponseEntity.ok(dtoList);
        } catch (Exception e) {
            log.error("Error mengambil data obat!");
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
