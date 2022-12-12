package tk.apap.rumahsehat.restcontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tk.apap.rumahsehat.model.AppointmentModel;
import tk.apap.rumahsehat.service.AppointmentRestService;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/appointment")
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

    @PostMapping(value = "/add")
    private AppointmentModel createCourse(@Valid @RequestBody AppointmentModel course, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        } else {
            return appointmentRestService.createAppointment(course);
        }
    }

    //retrieve
    @GetMapping(value = "/{kode}")
    private AppointmentModel retrieveCourse(@PathVariable("kode") String code){
        try{
            return appointmentRestService.getAppointmentByCode(code);
        } catch(NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Code Course" + code + " not found"
            );
        }
    }
}