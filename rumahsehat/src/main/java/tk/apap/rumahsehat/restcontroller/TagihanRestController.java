package tk.apap.rumahsehat.restcontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tk.apap.rumahsehat.model.TagihanModel;
import tk.apap.rumahsehat.service.PasienRestService;
import tk.apap.rumahsehat.service.TagihanRestService;

import java.util.List;
import java.util.NoSuchElementException;

//@Slf4j
@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class TagihanRestController {

    @Autowired
    private TagihanRestService tagihanRestService;


    //Retrieve Tagihan
    @GetMapping(value = "/tagihan/{kode}")
    private TagihanModel retrieveTagihan(@PathVariable("kode") String kode){
        try{
            return tagihanRestService.getTagihanByKode(kode);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,  "Tagihan dengan kode " + kode + " tidak ditemukan"
            );
        }
    }
}
