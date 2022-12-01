package tk.apap.rumahsehat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tk.apap.rumahsehat.model.DokterModel;
import tk.apap.rumahsehat.repository.DokterDb;

@Service
public class DokterServiceImpl implements DokterService {
  @Autowired
  private DokterDb dokterDb;

  @Override
  public DokterModel addDokter(DokterModel dokter) {
    String pass = encrypt(dokter.getPassword());
    dokter.setPassword(pass);
    return dokterDb.save(dokter);
  }

  @Override
  public String encrypt(String password) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String hashedPassword = passwordEncoder.encode(password);
    return hashedPassword;
  }

  @Override
  public DokterModel getDokterByUsername(String username) {
    return dokterDb.findByUsername(username);
  }

  @Override
  public List<DokterModel> getListDokter() {
    return dokterDb.findAll();
  }

  // @Override
  // public void deleteDokter(DokterModel dokter) {
  //   dokterDb.delete(dokter);
  // }

  @Override
  public DokterModel getDokterByUuid(String uuid) {
    List<DokterModel> listDokter = getListDokter();
    for (int i = 0; i < listDokter.size(); i++) {
      if (listDokter.get(i).getUuid().equals(uuid)) {
        DokterModel dokterTarget = listDokter.get(i);
        return dokterTarget;
      }
    }
    return null;
  }

}
