package tk.apap.rumahsehat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import tk.apap.rumahsehat.model.JumlahIdModel;
import tk.apap.rumahsehat.model.JumlahModel;
import tk.apap.rumahsehat.model.ObatModel;
import tk.apap.rumahsehat.model.ResepModel;
import tk.apap.rumahsehat.repository.JumlahDb;
import tk.apap.rumahsehat.repository.ObatDb;
import tk.apap.rumahsehat.repository.ResepDb;

@Service
@Transactional
public class ResepServiceImpl implements ResepService {

    @Autowired
    ResepDb resepDb;

    @Autowired
    ObatDb obatDb;

    @Autowired
    JumlahDb jumlahDb;

    @Override
    public void addResep(ResepModel resep) {
        resepDb.save(resep);
    }

    @Override
    public List<ResepModel> getListResep() {
        return resepDb.findAll();
    }

    @Override
    public void deleteResep(ResepModel resep) {
        resepDb.delete(resep);
    }

    @Override
    public ResepModel getResepById(Long id) {
        Optional<ResepModel> resep = resepDb.findResepById(id);
        if (resep.isPresent()) {
            return resep.get();
        }
        else {
            return null;
        }
    }

//    @Override
//    public List<JumlahModel> getListJumlah() {
//        return jumlahDb.findall();
//    }

    @Override
    public List<ObatModel> getListObat() {
        return obatDb.findAll();
    }

    @Override
    public List<JumlahModel> getListJumlah() {
        return jumlahDb.findAll();
    }
}
