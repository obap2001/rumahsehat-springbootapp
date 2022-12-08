package tk.apap.rumahsehat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.apap.rumahsehat.model.JumlahModel;
import tk.apap.rumahsehat.model.ObatModel;
import tk.apap.rumahsehat.repository.JumlahDb;
import tk.apap.rumahsehat.repository.ObatDb;

import javax.transaction.Transactional;

@Service
@Transactional
public class JumlahServiceImpl implements JumlahService {
    @Autowired
    JumlahDb jumlahDb;

    @Override
    public void addJumlah(JumlahModel jumlah) {
        jumlahDb.save(jumlah);
    }
}
