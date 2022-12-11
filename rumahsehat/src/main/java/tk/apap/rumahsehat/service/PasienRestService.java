package tk.apap.rumahsehat.service;

// import org.springframework.stereotype.Service;
import tk.apap.rumahsehat.model.PasienModel;
import tk.apap.rumahsehat.model.TagihanModel;

// import javax.transaction.Transactional;
import java.util.*;

public interface PasienRestService {
    PasienModel registerPasien(PasienModel pasien);
    public String encrypt(String password);
    List<PasienModel> retrieveListPasien();
    Map<String, Object> retrievePasienByTagihan(String kode);

    PasienModel updateSaldo(PasienModel pasienModel);
    PasienModel retrievePasienByUsername(String username);
    List<TagihanModel> retrieveTagihanByPasien(PasienModel pasien);
}
