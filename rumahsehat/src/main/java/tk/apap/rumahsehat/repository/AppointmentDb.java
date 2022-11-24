package tk.apap.rumahsehat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tk.apap.rumahsehat.model.AppointmentModel;
import tk.apap.rumahsehat.model.UserModel;

@Repository
public interface AppointmentDb extends JpaRepository<AppointmentModel, Long> {
    //AppointmentModel findByPatientName(String patientName);

    List<AppointmentModel> findAll();

    Optional<AppointmentModel> findByKode(String kode);
    //void delete(UserModel user);
}