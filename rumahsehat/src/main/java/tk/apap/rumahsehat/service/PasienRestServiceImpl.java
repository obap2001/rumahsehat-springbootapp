package tk.apap.rumahsehat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tk.apap.rumahsehat.model.PasienModel;
import tk.apap.rumahsehat.model.TagihanModel;
import tk.apap.rumahsehat.repository.PasienDb;
import tk.apap.rumahsehat.repository.TagihanDb;


import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class PasienRestServiceImpl implements PasienRestService{
    @Autowired
    PasienDb pasienDb;

    @Autowired
    TagihanDb tagihanDb;
    @Override
    public PasienModel registerPasien(PasienModel pasien) {
        String pass = encrypt(pasien.getPassword());
        pasien.setPassword(pass);
        return pasienDb.save(pasien);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public List<PasienModel> retrieveListPasien(){
        return pasienDb.findAll();
    }

    @Override
    public PasienModel retrievePasienByUsername(String username){
        PasienModel pasien = pasienDb.findByUsername(username);
        return pasien;
    }

    @Override
    public Map<String, Object> retrievePasienByTagihan(String kode) {
        Optional<TagihanModel> tagihanOptional = tagihanDb.findById(kode);
        TagihanModel tagihan = null;
        if (tagihanOptional.isPresent()){
            tagihan = tagihanOptional.get();
        } else {
            throw new NoSuchElementException();
        }
        PasienModel pasien = tagihan.getAppointment().getPasien();
        Map<String, Object> map = new HashMap<>();
        map.put("id", pasien.getUuid());
        map.put("username", pasien.getUsername());
        map.put("email", pasien.getEmail());
        map.put("nama", pasien.getNama());
        map.put("saldo", pasien.getSaldo());
        map.put("umur", pasien.getUmur());
        return map;
    }

    @Override
    public PasienModel updateSaldo(PasienModel pasienModel){
        return pasienDb.save(pasienModel);
    }
}
