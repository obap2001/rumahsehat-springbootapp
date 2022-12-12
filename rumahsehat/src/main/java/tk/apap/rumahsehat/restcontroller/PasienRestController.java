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
import tk.apap.rumahsehat.service.PasienService;
import tk.apap.rumahsehat.service.TagihanRestService;
import tk.apap.rumahsehat.service.UserService;

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
    private PasienModel registerPasien(@Valid @RequestBody PasienModel pasien, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else {
            log.info("api register pasien berhasil");
            return pasienRestService.registerPasien(pasien);
        }
    }

    @GetMapping(value = "/tagihan/{kode}")
    private Map retrievePasienByTagihan(@PathVariable("kode") String kode){
        return pasienRestService.retrievePasienByTagihan(kode);
    }
    @CrossOrigin(value = "*", maxAge = 3600)
    @GetMapping("/data-pasien")
    private PasienModel retrievePasienById(@RequestHeader("Authorization") String token) {
        System.out.println(token);
        Map<String, String> decodedToken = decode(token);
        String username = decodedToken.get("sub");
        System.out.println(username);
        PasienModel pasien = pasienRestService.retrievePasienByUsername(username);
        log.info("api mengambil data pasien");
        return pasien;
    }

    private Map<String, String> decode(String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        Gson gson = new Gson();
        Map<String, String> decodedToken = gson.fromJson(payload, new TypeToken<Map<String, String>>() {}.getType());
        return decodedToken;
    }

    @PostMapping(value = "/topup-saldo")
    private PasienModel topUpSaldo(@RequestHeader("Authorization") String token, @RequestBody PasienModel pasien) {
        Map<String, String> decodedToken = decode(token);
        String username = decodedToken.get("sub");
        PasienModel pasienlama = pasienRestService.retrievePasienByUsername(username);
        pasienlama.setSaldo(pasienlama.getSaldo()+pasien.getSaldo());
        pasien = pasienlama;
        pasienRestService.updateSaldo(pasien);
        return pasien;
    }

    //Retrieve List All Tagihan
    @GetMapping(value = "/tagihan/viewall")
    private List<TagihanModel> retrieveListTagihanPasien(@RequestHeader("Authorization") String token){
        Map<String, String> decodedToken = decode(token);
        String username = decodedToken.get("sub");
        PasienModel pasien = pasienRestService.retrievePasienByUsername(username);
        return pasienRestService.retrieveTagihanByPasien(pasien);
    }

    //Retrieve Detail Tagihan Pasien
    @GetMapping(value = "/tagihan/detail/{kode}")
    private TagihanModel retrieveTagihan(@PathVariable("kode") String kode){
        try{
            return tagihanRestService.getTagihanByKode(kode);
        } catch (NoSuchElementException e){
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
                return ResponseEntity.ok("Success");
            }throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo tidak mencukupi untuk membayar tagihan");
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  "Tagihan dengan kode " + kode + " tidak ditemukan");
        }
    }
}

