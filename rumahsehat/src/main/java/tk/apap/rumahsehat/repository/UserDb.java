package tk.apap.rumahsehat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tk.apap.rumahsehat.model.UserModel;

@Repository
public interface UserDb extends JpaRepository<UserModel, String> {
  UserModel findByUsername(String username);
  List<UserModel> findAll();
  void delete(UserModel user);
}
