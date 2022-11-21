package tk.apap.rumahsehat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.apap.rumahsehat.model.ObatModel;
import tk.apap.rumahsehat.repository.ObatDb;

import java.util.List;
import java.util.Optional;

@Service
public class ObatServiceImpl implements ObatService{
    @Autowired
    private ObatDb obatDb;

    @Override
    public List<ObatModel> getListObat(){
        return obatDb.findAll();
    }

    @Override
    public ObatModel updateObat(ObatModel obat) {
        obatDb.save(obat);
        return obat;
    }

    @Override
    public ObatModel getObatById(String id) {
        Optional<ObatModel> obat = obatDb.findById(id);
        if (obat.isPresent()) {
            return obat.get();
        }
        return null;
    }
}
