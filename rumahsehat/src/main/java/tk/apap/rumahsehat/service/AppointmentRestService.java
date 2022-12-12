package tk.apap.rumahsehat.service;

import tk.apap.rumahsehat.model.AppointmentModel;

import java.util.List;

public interface AppointmentRestService {
    List<AppointmentModel> retrieveListAppointment();

    AppointmentModel createAppointment(AppointmentModel course);

    AppointmentModel getAppointmentByCode(String code);
}
