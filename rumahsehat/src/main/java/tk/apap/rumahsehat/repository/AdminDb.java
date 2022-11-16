package tk.apap.rumahsehat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tk.apap.rumahsehat.model.AdminModel;

@Repository
public interface AdminDb extends JpaRepository<AdminModel, Long> {
  AdminModel findByUsername(String username);
  List<AdminModel> findAll();
  void delete(AdminModel user);
}
