package tk.apap.rumahsehat.service;

import org.springframework.beans.factory.annotation.Autowired;
import tk.apap.rumahsehat.model.PasienModel;
import tk.apap.rumahsehat.repository.PasienDb;

import java.util.List;

public class PasienRestServiceImpl implements PasienRestService{
    @Autowired
    PasienDb pasienDb;

    @Override
    public List<PasienModel> retrieveListPasien(){
        return pasienDb.findAll();
    }
}
