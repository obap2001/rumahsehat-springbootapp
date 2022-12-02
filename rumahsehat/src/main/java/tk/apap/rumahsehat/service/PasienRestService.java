package tk.apap.rumahsehat.service;

import org.springframework.stereotype.Service;
import tk.apap.rumahsehat.model.PasienModel;

import javax.transaction.Transactional;
import java.util.List;

public interface PasienRestService {
    List<PasienModel> retrieveListPasien();
<<<<<<< HEAD
    PasienModel updateSaldo(Long id, int nominal);
    PasienModel retrievePasien(Long idPasien);
=======
    //PasienModel updateSaldo(Long id, int nominal);
>>>>>>> 06c856af4a41a2834ae61853c27e9e4f30afc33a
}
