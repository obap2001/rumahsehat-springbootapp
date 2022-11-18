package tk.apap.rumahsehat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.apap.rumahsehat.model.ResepModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResepDb extends JpaRepository<ResepModel, Long> {
    Optional<ResepModel> findResepById(Long id);
    List<ResepModel> findAll();
    void delete(ResepModel resep);
}
