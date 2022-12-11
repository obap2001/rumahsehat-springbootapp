package tk.apap.rumahsehat.service;

// import org.springframework.stereotype.Service;
import tk.apap.rumahsehat.model.PasienModel;
import tk.apap.rumahsehat.model.TagihanModel;

// import javax.transaction.Transactional;
import java.util.List;

public interface PasienRestService {
    PasienModel registerPasien(PasienModel pasien);
    public String encrypt(String password);
    List<PasienModel> retrieveListPasien();
    PasienModel updateSaldo(String id, int nominal);
    PasienModel retrievePasien(String idPasien);
    List<TagihanModel> retrieveTagihanByPasien(PasienModel pasien);
}
