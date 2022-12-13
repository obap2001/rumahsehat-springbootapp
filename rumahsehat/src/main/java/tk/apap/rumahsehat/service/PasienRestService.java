package tk.apap.rumahsehat.service;

import tk.apap.rumahsehat.model.PasienModel;
import java.util.*;

public interface PasienRestService {
    PasienModel registerPasien(PasienModel pasien);
    public String encrypt(String password);
    List<PasienModel> retrieveListPasien();
    Map<String, Object> retrievePasienByTagihan(String kode);

    PasienModel updateSaldo(PasienModel pasienModel);
    PasienModel retrievePasienByUsername(String username);
    Map<String, Object>  retrieveTagihanByPasien(PasienModel pasien);
    PasienModel retrievePasien(String id);
}
