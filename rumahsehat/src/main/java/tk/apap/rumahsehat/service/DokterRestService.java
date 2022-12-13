package tk.apap.rumahsehat.service;

import tk.apap.rumahsehat.model.DokterModel;
import java.util.List;

public interface DokterRestService {
    List<DokterModel> retrieveListDokter();

}
