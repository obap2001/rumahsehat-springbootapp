package tk.apap.rumahsehat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tk.apap.rumahsehat.model.JumlahIdModel;
import tk.apap.rumahsehat.model.JumlahModel;

import java.util.List;

@Repository
public interface JumlahDb extends JpaRepository<JumlahModel, JumlahIdModel> {
    @Query("SELECT DISTINCT j FROM JumlahModel j WHERE j.resep.id = :idResep")
    List<JumlahModel> findJumlahByResep(@Param("idResep") Long idResep);
}
