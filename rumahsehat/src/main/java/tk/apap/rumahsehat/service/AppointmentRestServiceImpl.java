package tk.apap.rumahsehat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
//import tk.apap.rumahsehat.Setting.Setting;
import tk.apap.rumahsehat.model.AppointmentModel;
import tk.apap.rumahsehat.model.ObatModel;
import tk.apap.rumahsehat.repository.AppointmentDb;
import tk.apap.rumahsehat.repository.ObatDb;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class AppointmentRestServiceImpl implements AppointmentRestService{
    @Autowired
    private AppointmentDb appointmentDb;

    @Override
    public List<AppointmentModel> retrieveListAppointment(){
        return appointmentDb.findAll();
    }

    @Override
    public AppointmentModel createAppointment(AppointmentModel appointment){
        return appointmentDb.save(appointment);
    }

    @Override
    public AppointmentModel getAppointmentByCode(String code){
        Optional<AppointmentModel> appointment = appointmentDb.getAppointmentByCode(code);
        if(appointment.isPresent()){
            return appointment.get();
        } else {
            throw new NoSuchElementException();
        }

    }
}