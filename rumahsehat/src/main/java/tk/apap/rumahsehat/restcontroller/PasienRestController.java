package tk.apap.rumahsehat.restcontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.apap.rumahsehat.model.PasienModel;
import tk.apap.rumahsehat.service.PasienRestService;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class PasienRestController {
    @Autowired
    private PasienRestService pasienRestService;

    //retrieve all
    @GetMapping("/data-pasien")
    public ResponseEntity getDataPasien() {
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

    //top up saldo
    @PutMapping(value = "/{idPasien}/update-saldo")
    private PasienModel topUpSaldoPasien(@PathVariable("idPasien") Long idPasien, @RequestParam Integer newSaldo){
        try{
            return pasienRestService.updateSaldo(idPasien, newSaldo);
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pasien dengan id " + idPasien + " tidak ditemukan."
            );
        }
    }

    @GetMapping(value = "/pasien/{idPasien}")
    private PasienModel retrievePengajar(@PathVariable("idPasien") Long idPasien) {
        try {
            return pasienRestService.retrievePasien(idPasien);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pasien dengan id " + idPasien + " tidak ditemukan."
            );
        }
    }
}
