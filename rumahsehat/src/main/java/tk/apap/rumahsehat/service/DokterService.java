package tk.apap.rumahsehat.service;

import java.util.List;

import tk.apap.rumahsehat.model.AppointmentModel;
import tk.apap.rumahsehat.model.DokterModel;


public interface DokterService {
  DokterModel addDokter(DokterModel dokter);
  public String encrypt(String password);
  DokterModel getDokterByUsername(String username);
  List<DokterModel> getListDokter();

  List<AppointmentModel> getListAppointment();

    //List<AppointmentModel> getListAppointment();
  // void deleteDokter(DokterModel dokter);
}
