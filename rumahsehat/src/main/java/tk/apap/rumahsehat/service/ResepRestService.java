package tk.apap.rumahsehat.service;
import org.springframework.stereotype.Service;
import tk.apap.rumahsehat.model.ResepModel;

import javax.transaction.Transactional;
import java.util.List;
public interface ResepRestService {
    List<ResepModel> retrieveListResep();
    ResepModel getResepByKodeAppointment(String kode);
}
