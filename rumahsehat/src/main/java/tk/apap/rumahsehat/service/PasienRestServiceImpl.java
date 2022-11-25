package tk.apap.rumahsehat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.apap.rumahsehat.model.PasienModel;
import tk.apap.rumahsehat.repository.PasienDb;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PasienRestServiceImpl implements PasienRestService{
    @Autowired
    PasienDb pasienDb;

    @Override
    public List<PasienModel> retrieveListPasien(){
        return pasienDb.findAll();
    }
    @Override
    public PasienModel updateSaldo(Long id, int nominal){
        Optional<PasienModel> pasien = pasienDb.findById(id);
        if (pasien.isPresent()){
            PasienModel pasienUpdt = pasien.get();
            pasienUpdt.setSaldo(pasienUpdt.getSaldo()+nominal);
            return pasienUpdt;
        }else return null;
    }
}
