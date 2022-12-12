package tk.apap.rumahsehat.restcontroller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tk.apap.rumahsehat.model.ResepModel;
import tk.apap.rumahsehat.service.ResepRestService;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/resep/")
public class ResepRestController {
    @Autowired
    private ResepRestService resepRestService;

    private Map<String, String> decode(String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        Gson gson = new Gson();
        Map<String, String> decodedToken = gson.fromJson(payload, new TypeToken<Map<String, String>>() {}.getType());
        return decodedToken;
    }



    @CrossOrigin(value = "*", maxAge = 3600)
    @GetMapping(value = "/resep/view/{kode}")
    public ResepModel detailResep(@PathVariable("kode") String kode) {
//        ResponseEntity responseEntity = null;
        try {
            ResepModel resepTarget = resepRestService.getResepByKodeAppointment(kode);
            log.info("mengambil target resep");
//            responseEntity = resepTarget;
            return resepTarget;
        }
        catch (Exception e) {
            log.error("gagal mengambil resep");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
