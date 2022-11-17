package tk.apap.rumahsehat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tk.apap.rumahsehat.model.PasienModel;

@Repository
public interface PasienDb extends JpaRepository<PasienModel, Long> {
  PasienModel findByUsername(String username);
  List<PasienModel> findAll();
  void delete(PasienModel appasienoteker);
}
