package tk.apap.rumahsehat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.apap.rumahsehat.model.TagihanModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagihanDb extends JpaRepository<TagihanModel, String> {
    List<TagihanModel> findAll();
    Optional<TagihanModel> findByKode(String kode);
}