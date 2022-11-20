package tk.apap.rumahsehat.service;

import java.util.List;

import tk.apap.rumahsehat.model.AdminModel;
import tk.apap.rumahsehat.model.AppointmentModel;
import tk.apap.rumahsehat.repository.AppointmentDb;


public interface AppointmentService {
    AppointmentModel addAppointment(AppointmentModel appointment);

    //AppointmentModel getAppointmentByPatientName(String patientName);

    List<AppointmentModel> getListAppointment();

}
