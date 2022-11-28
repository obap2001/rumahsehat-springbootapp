package tk.apap.rumahsehat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tk.apap.rumahsehat.model.PasienModel;

@Repository
public interface PasienDb extends JpaRepository<PasienModel, String> {
  PasienModel findByUsername(String username);
  List<PasienModel> findAll();
  void delete(PasienModel appasienoteker);

//  @Query("SELECT p FROM PasienModel p WHERE p.username = :username")
//  Optional<PasienModel> findByUsername(@Param("username") String username);
}
