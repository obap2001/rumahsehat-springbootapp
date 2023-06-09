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
public interface AppointmentDb extends JpaRepository<AppointmentModel, String> {

    List<AppointmentModel> findAll();

    List<AppointmentModel> getListAppointmentModelByDokter(DokterModel dokter);

    @Query("SELECT a FROM AppointmentModel a WHERE a.kode = :kode")
    AppointmentModel getAppointmentById(@Param("kode") String kode);

    @Query("SELECT a FROM AppointmentModel a WHERE a.kode = :kode")
    Optional<AppointmentModel> getAppointmentByCode(@Param("kode") String kode);
}