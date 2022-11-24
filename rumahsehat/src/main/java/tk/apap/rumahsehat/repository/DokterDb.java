package tk.apap.rumahsehat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tk.apap.rumahsehat.model.AppointmentModel;
import tk.apap.rumahsehat.model.DokterModel;

@Repository
public interface DokterDb extends JpaRepository<DokterModel, Long> {
  DokterModel findByUsername(String username);
  List<DokterModel> findAll();
  void delete(DokterModel dokter);
  List<AppointmentModel> getListAppointment();
}
