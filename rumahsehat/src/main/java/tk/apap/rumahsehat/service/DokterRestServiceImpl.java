package tk.apap.rumahsehat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.apap.rumahsehat.model.DokterModel;
import tk.apap.rumahsehat.model.PasienModel;
import tk.apap.rumahsehat.repository.DokterDb;
import tk.apap.rumahsehat.repository.PasienDb;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DokterRestServiceImpl implements DokterRestService{
    @Autowired
    DokterDb dokterDb;

    @Override
    public List<DokterModel> retrieveListDokter(){
        return dokterDb.findAll();
    }

}
