package tk.apap.rumahsehat.service;

import org.springframework.stereotype.Service;
import tk.apap.rumahsehat.model.DokterModel;
import tk.apap.rumahsehat.model.PasienModel;

import javax.transaction.Transactional;
import java.util.List;

public interface DokterRestService {
    List<DokterModel> retrieveListDokter();

}
