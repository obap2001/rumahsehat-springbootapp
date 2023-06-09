package tk.apap.rumahsehat.restcontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.apap.rumahsehat.model.DokterModel;
import tk.apap.rumahsehat.service.DokterRestService;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin("https://apap-078.cs.ui.ac.id/")
@RequestMapping("/api")
public class DokterRestController {
    @Autowired
    private DokterRestService dokterRestService;

    //retrieve all
    @GetMapping("/data-dokter")
    public ResponseEntity getDataDokter() {
        log.info("api mengambil data semua dokter");
        ResponseEntity responseEntity = null;
        try {
            List<DokterModel> dtoList = dokterRestService.retrieveListDokter();
            responseEntity = ResponseEntity.ok(dtoList);
        } catch (Exception e) {
            log.error("Error mengambil data dokter!");
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}

