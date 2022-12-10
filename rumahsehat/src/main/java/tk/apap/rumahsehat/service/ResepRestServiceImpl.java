package tk.apap.rumahsehat.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tk.apap.rumahsehat.model.ResepModel;
import tk.apap.rumahsehat.repository.ResepDb;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ResepRestServiceImpl implements ResepRestService {
    @Autowired
    private ResepDb resepDb;

    @Override
    public List<ResepModel> retrieveListResep() {
        return resepDb.findAll();
    }
}
