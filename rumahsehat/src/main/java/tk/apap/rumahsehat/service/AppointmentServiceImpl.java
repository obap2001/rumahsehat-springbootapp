package tk.apap.rumahsehat.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tk.apap.rumahsehat.model.AppointmentModel;
import tk.apap.rumahsehat.model.ResepModel;
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


}
