package tk.apap.rumahsehat.restcontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.apap.rumahsehat.model.AppointmentModel;
import tk.apap.rumahsehat.service.AppointmentRestService;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("appointment/api")
public class AppointmentRestController {
    @Autowired
    private AppointmentRestService appointmentRestService;

    //retrieve all
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
}