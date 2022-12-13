package tk.apap.rumahsehat.restcontroller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.apap.rumahsehat.model.PasienModel;
import tk.apap.rumahsehat.model.TagihanModel;
import tk.apap.rumahsehat.service.PasienRestService;
import tk.apap.rumahsehat.service.TagihanRestService;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/api/pasien")
public class PasienRestController {
    @Autowired
    private PasienRestService pasienRestService;

    @Autowired
    private TagihanRestService tagihanRestService;

    @CrossOrigin
    @PostMapping(value="/register")
    public PasienModel registerPasien(@Valid @RequestBody PasienModel pasien, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            log.error("api register pasien gagal, invalid credetials");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else {
            log.info("api register pasien berhasil");
            return pasienRestService.registerPasien(pasien);
        }
    }

    @GetMapping(value = "/tagihan/{kode}")
    public Map retrievePasienByTagihan(@PathVariable("kode") String kode){
        return pasienRestService.retrievePasienByTagihan(kode);
    }
    @CrossOrigin(value = "*", maxAge = 3600)
    @GetMapping("/data-pasien")
    private PasienModel retrievePasienByUsername(@RequestHeader("Authorization") String token) {
        Map<String, String> decodedToken = decode(token);
        String username = decodedToken.get("sub");
        PasienModel pasien = pasienRestService.retrievePasienByUsername(username);
        log.info("api mengambil data pasien");
        return pasien;
    }

    public Map<String, String> decode(String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        Gson gson = new Gson();
        Map<String, String> decodedToken = gson.fromJson(payload, new TypeToken<Map<String, String>>() {}.getType());
        return decodedToken;
    }

    @PostMapping(value = "/topup-saldo")
    public PasienModel topUpSaldo(@RequestHeader("Authorization") String token, @RequestBody PasienModel pasien) {
        Map<String, String> decodedToken = decode(token);
        String username = decodedToken.get("sub");
        PasienModel pasienlama = pasienRestService.retrievePasienByUsername(username);
        pasienlama.setSaldo(pasienlama.getSaldo()+pasien.getSaldo());
        pasien = pasienlama;
        pasienRestService.updateSaldo(pasien);
        log.info("api update saldo pasien berhasil");
        return pasien;
    }

    //Retrieve List All Tagihan
    @GetMapping(value = "/tagihan/viewall")
    public Map<String,Object> retrieveListTagihanPasien(@RequestHeader("Authorization") String token){
        Map<String, String> decodedToken = decode(token);
        String username = decodedToken.get("sub");
        PasienModel pasien = pasienRestService.retrievePasienByUsername(username);
        Map<String,Object> map= pasienRestService.retrieveTagihanByPasien(pasien);
        if(!map.isEmpty()){
            log.info("api mengambil data tagihan pasien berhasil");
            return map;
        }else{
            log.info("api mengambil data tagihan pasien gagal, tagihan tidak ditemukan");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,  pasien.getUsername() + "Tidak memiliki tagihan.");
        }
    }

    //Retrieve Detail Tagihan Pasien
    @GetMapping(value = "/tagihan/detail/{kode}")
    public TagihanModel retrieveTagihan(@RequestHeader("Authorization") String token, @PathVariable("kode") String kode){
        try{
            log.info("api mengambil detail tagihan pasien berhasil");
            return tagihanRestService.getTagihanByKode(kode);
        } catch (NoSuchElementException e){
            log.info("api mengambil detail tagihan pasien gagal, tagihan tidak ditemukan");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,  "Tagihan dengan kode " + kode + " tidak ditemukan"
            );
        }
    }

    //Bayar Tagihan
    @PostMapping(value = "/tagihan/bayar/{kode}")
    private ResponseEntity bayarTagihan(@RequestHeader("Authorization") String token, @RequestBody PasienModel pasien, @PathVariable("kode") String kode){
        Map<String, String> decodedToken = decode(token);
        String username = decodedToken.get("sub");
        PasienModel pasienlama = pasienRestService.retrievePasienByUsername(username);
        TagihanModel tagihan = tagihanRestService.getTagihanByKode(kode);
        try{
            if (tagihanRestService.saldoCukupBayarTagihan(tagihan.getJumlahTagihan(), pasien.getSaldo())) {
                pasienlama.setSaldo(pasien.getSaldo() - tagihan.getJumlahTagihan());
                pasien = pasienlama;
                pasienRestService.updateSaldo(pasien);
                tagihan.setIsPaid(true);
                log.info("api membayar tagihan pasien berhasil");
                return ResponseEntity.ok("Success");
            }throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo tidak mencukupi untuk membayar tagihan");
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  "Tagihan dengan kode " + kode + " tidak ditemukan");
        }
    }
}

