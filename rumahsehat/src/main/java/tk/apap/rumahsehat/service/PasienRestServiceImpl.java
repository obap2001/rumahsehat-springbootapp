package tk.apap.rumahsehat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.apap.rumahsehat.model.PasienModel;
import tk.apap.rumahsehat.repository.PasienDb;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PasienRestServiceImpl implements PasienRestService{
    @Autowired
    PasienDb pasienDb;

    @Override
    public List<PasienModel> retrieveListPasien(){
        return pasienDb.findAll();
    }
}
