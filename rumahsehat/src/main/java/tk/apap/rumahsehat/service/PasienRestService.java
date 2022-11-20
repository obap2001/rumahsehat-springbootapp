package tk.apap.rumahsehat.service;

import org.springframework.stereotype.Service;
import tk.apap.rumahsehat.model.PasienModel;

import javax.transaction.Transactional;
import java.util.List;

@Service@Transactional
public interface PasienRestService {
    List<PasienModel> retrieveListPasien();
}
