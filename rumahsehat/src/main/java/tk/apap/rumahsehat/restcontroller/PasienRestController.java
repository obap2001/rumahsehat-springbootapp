package tk.apap.rumahsehat.restcontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.apap.rumahsehat.model.PasienModel;
import tk.apap.rumahsehat.service.PasienRestService;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class PasienRestController {
    @Autowired
    private PasienRestService pasienRestService;

    //retrieve all
    @GetMapping("/data-pasien")
    public ResponseEntity getDataObat() {
        log.info("api mengambil data semua pasien");
        ResponseEntity responseEntity = null;
        try {
            List<PasienModel> dtoList = pasienRestService.retrieveListPasien();
            responseEntity = ResponseEntity.ok(dtoList);
        } catch (Exception e) {
            log.error("Error mengambil data pasien!");
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
