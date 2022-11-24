package tk.apap.rumahsehat.service;

import java.util.List;

import tk.apap.rumahsehat.model.AppointmentModel;
import tk.apap.rumahsehat.model.PasienModel;


public interface PasienService {
  PasienModel addPasien(PasienModel pasien);
  public String encrypt(String password);
  PasienModel getPasienByUsername(String username);
  List<PasienModel> getListPasien();

  List<AppointmentModel> getListAppointment();
    // void deletePasien(PasienModel apoteker);
}
