package tk.apap.rumahsehat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tk.apap.rumahsehat.model.ApotekerModel;

@Repository
public interface ApotekerDb extends JpaRepository<ApotekerModel, Long> {
  ApotekerModel findByUsername(String username);
  List<ApotekerModel> findAll();
  void delete(ApotekerModel apoteker);
}
