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
import tk.apap.rumahsehat.model.AppointmentModel;
import tk.apap.rumahsehat.model.PasienModel;
import tk.apap.rumahsehat.service.AppointmentRestService;
import tk.apap.rumahsehat.service.PasienRestService;

import javax.validation.Valid;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/appointment")
public class AppointmentRestController {
    @Autowired
    private AppointmentRestService appointmentRestService;

    @Autowired
    private PasienRestService pasienRestService;

    //retrieve all
    @CrossOrigin
    @GetMapping("/list-appointment")
    public ResponseEntity getDataAppointment() {
        log.info("api mengambil data semua appointment");
        ResponseEntity responseEntity = null;
        try {
            List<AppointmentModel> dtoList = appointmentRestService.retrieveListAppointment();
            responseEntity = ResponseEntity.ok(dtoList);
        } catch (Exception e) {
            log.error("Error mengambil data appointment!");
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @GetMapping("/listappointment")
    private List<AppointmentModel> retrieveListAppointment(){
        return appointmentRestService.retrieveListAppointment();
    }

    @PostMapping(value = "/add")
    private AppointmentModel createAppointment(@Valid @RequestBody AppointmentModel course, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        } else {
            return appointmentRestService.createAppointment(course);
        }
    }

    @PostMapping(value = "/create")
    private PasienModel createAppointment(@RequestHeader("Authorization") String token, @RequestBody PasienModel pasien, @RequestBody AppointmentModel appointment) {
        Map<String, String> decodedToken = decode(token);
        String username = decodedToken.get("sub");
        PasienModel pasienlama = pasienRestService.retrievePasienByUsername(username);
        appointment = appointmentRestService.createAppointment(appointment);

        List<AppointmentModel> listAppointmentPasien = pasien.getListAppointment();
        listAppointmentPasien.add(appointment);

        pasien = pasienlama;
        pasienRestService.updateSaldo(pasien);
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
    //retrieve
    @GetMapping(value = "/{kode}")
    private AppointmentModel retrieveAppointment(@PathVariable("kode") String code){
        try{
            return appointmentRestService.getAppointmentByCode(code);
        } catch(NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Code Course" + code + " not found"
            );
        }
    }
}