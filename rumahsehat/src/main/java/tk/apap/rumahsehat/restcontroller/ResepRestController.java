package tk.apap.rumahsehat.restcontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.apap.rumahsehat.model.ResepModel;
import tk.apap.rumahsehat.service.ResepRestService;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ResepRestController {
    @Autowired
    private ResepRestService resepRestService;

    @GetMapping("/list-resep")
    public ResponseEntity getDataResep() {
        log.info("save all resep");
        ResponseEntity responseEntity = null;

        try {
            List<ResepModel> resepList = resepRestService.retrieveListResep();
            responseEntity = ResponseEntity.ok(resepList);
        }
        catch (Exception e) {
            log.error("failed save resep");
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return  responseEntity;
    }

    @GetMapping("/listresep")
    private List<ResepModel> retriebeListResep() {
        return resepRestService.retrieveListResep();
    }
}
