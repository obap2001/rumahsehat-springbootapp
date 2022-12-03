package tk.apap.rumahsehat.service;

import java.util.List;
import java.util.Optional;

import tk.apap.rumahsehat.model.AppointmentModel;
import tk.apap.rumahsehat.model.DokterModel;


public interface AppointmentService {
    AppointmentModel addAppointment(AppointmentModel appointment);

    //AppointmentModel getAppointmentByPatientName(String patientName);

    List<AppointmentModel> getListAppointment();

    List<AppointmentModel> getListAppointmentByDokter(DokterModel dokter);

    AppointmentModel getAppointmentByKode(String kode);

    AppointmentModel getAppointmentById(String kode);
}
