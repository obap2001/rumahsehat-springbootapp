package tk.apap.rumahsehat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tk.apap.rumahsehat.model.AppointmentModel;
import tk.apap.rumahsehat.model.DokterModel;
import tk.apap.rumahsehat.model.UserModel;

@Repository
public interface AppointmentDb extends JpaRepository<AppointmentModel, Long> {
    //AppointmentModel findByPatientName(String patientName);

    List<AppointmentModel> findAll();

    //Optional<AppointmentModel> findAppointmentById(String id);
    //void delete(UserModel user);
    List<AppointmentModel> getListAppointmentModelByDokter(DokterModel dokter);

    @Query("SELECT a FROM AppointmentModel a WHERE a.kode = :kode")
    AppointmentModel getAppointmentById(@Param("kode") String kode);

    //AppointmentModel getAppointmentById(String id);
}