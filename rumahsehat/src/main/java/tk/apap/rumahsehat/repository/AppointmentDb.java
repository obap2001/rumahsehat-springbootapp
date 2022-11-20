package tk.apap.rumahsehat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tk.apap.rumahsehat.model.AppointmentModel;
import tk.apap.rumahsehat.model.UserModel;

@Repository
public interface AppointmentDb extends JpaRepository<AppointmentModel, Long> {
    //AppointmentModel findByPatientName(String patientName);

    List<AppointmentModel> findAll();
    //void delete(UserModel user);
}