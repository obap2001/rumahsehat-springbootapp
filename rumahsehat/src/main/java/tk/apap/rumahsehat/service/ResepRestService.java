package tk.apap.rumahsehat.service;

import tk.apap.rumahsehat.model.ResepModel;
import java.util.List;

public interface ResepRestService {
    List<ResepModel> retrieveListResep();
    ResepModel getResepByKodeAppointment(String kode);
}
