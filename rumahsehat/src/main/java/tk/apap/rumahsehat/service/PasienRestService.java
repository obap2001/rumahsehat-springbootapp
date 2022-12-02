package tk.apap.rumahsehat.service;

import org.springframework.stereotype.Service;
import tk.apap.rumahsehat.model.PasienModel;

import javax.transaction.Transactional;
import java.util.List;

public interface PasienRestService {
    List<PasienModel> retrieveListPasien();
    PasienModel updateSaldo(Long id, int nominal);
    PasienModel retrievePasien(Long idPasien);
}
