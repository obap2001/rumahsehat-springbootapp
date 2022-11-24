package tk.apap.rumahsehat.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tk.apap.rumahsehat.model.AppointmentModel;
import tk.apap.rumahsehat.model.ObatModel;
import tk.apap.rumahsehat.repository.AppointmentDb;


@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentDb appointmentDb;

    @Override
    public AppointmentModel addAppointment(AppointmentModel appointment) {
        return appointmentDb.save(appointment);
    }

    @Override
    public List<AppointmentModel> getListAppointment() {
        return appointmentDb.findAll();
    }



    @Override
    public AppointmentModel getAppointmentByKode(String kode) {
        Optional<AppointmentModel> appointment = appointmentDb.findByKode(kode);
        if (appointment.isPresent()) {
            return appointment.get();
        }
        return null;
    }

    @Override
    public List<AppointmentModel> getAppointmentByDokter(String username) {
//        Optional<AppointmentModel> appointment = appointmentDb.findByDokter(username);
//        if (appointment.isPresent()) {
//            return appointment.get();
//        }
        return null;
    }


}
