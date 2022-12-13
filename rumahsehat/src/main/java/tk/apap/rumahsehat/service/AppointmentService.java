package tk.apap.rumahsehat.service;

import java.util.List;
import tk.apap.rumahsehat.model.AppointmentModel;
import tk.apap.rumahsehat.model.DokterModel;


public interface AppointmentService {
    AppointmentModel addAppointment(AppointmentModel appointment);

    List<AppointmentModel> getListAppointment();

    List<AppointmentModel> getListAppointmentByDokter(DokterModel dokter);

    AppointmentModel getAppointmentById(String kode);
}
