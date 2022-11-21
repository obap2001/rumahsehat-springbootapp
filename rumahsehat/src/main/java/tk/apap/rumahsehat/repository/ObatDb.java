package tk.apap.rumahsehat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.apap.rumahsehat.model.ObatModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface ObatDb extends JpaRepository<ObatModel, String> {
    List<ObatModel> findAll();
    Optional<ObatModel> findById(String id);
}
