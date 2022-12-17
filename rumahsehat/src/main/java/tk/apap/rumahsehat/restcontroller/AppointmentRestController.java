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
@CrossOrigin("https://apap-078.cs.ui.ac.id/")
@RestController
@RequestMapping("/api/appointment")
public class AppointmentRestController {
    @Autowired
    private AppointmentRestService appointmentRestService;

    @Autowired
    private PasienRestService pasienRestService;

    //retrieve all
    @CrossOrigin("https://apap-078.cs.ui.ac.id/")
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
    public List<AppointmentModel> retrieveListAppointment(){
        log.info("api mengambil data semua appointment");
        return appointmentRestService.retrieveListAppointment();
    }

    @PostMapping(value = "/add")
    public AppointmentModel createAppointmentModel(@Valid @RequestBody AppointmentModel course, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        } else {
            log.info("api menyimpan data baru appointment");
            return appointmentRestService.createAppointment(course);
        }
    }

    @PostMapping(value = "/create")
    public PasienModel createAppointmentModel2(@RequestHeader("Authorization") String token, @RequestBody PasienModel pasien, @RequestBody AppointmentModel appointment) {
        Map<String, String> decodedToken = decode(token);
        String username = decodedToken.get("sub");
        PasienModel pasienModel = pasienRestService.retrievePasienByUsername(username);
        appointment = appointmentRestService.createAppointment(appointment);

        List<AppointmentModel> listAppointmentPasien = pasien.getListAppointment();
        listAppointmentPasien.add(appointment);
        appointment.setPasien(pasienModel);
        appointment.setIsDone(false);

        pasien = pasienModel;
        log.info("api menyimpan data baru appointment");

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
    public AppointmentModel retrieveAppointment(@PathVariable("kode") String code){
        try{
            log.info("api mengambil data appointment");
            return appointmentRestService.getAppointmentByCode(code);
        } catch(NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Code Course" + code + " not found"
            );
        }
    }
}