package tk.apap.rumahsehat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.apap.rumahsehat.model.ObatModel;

import java.util.List;

@Repository
public interface ObatDb extends JpaRepository<ObatModel, String> {
    List<ObatModel> findAll();
}
