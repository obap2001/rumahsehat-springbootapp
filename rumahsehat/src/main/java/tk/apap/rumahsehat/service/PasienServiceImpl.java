package tk.apap.rumahsehat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tk.apap.rumahsehat.model.PasienModel;
import tk.apap.rumahsehat.repository.PasienDb;

@Service
public class PasienServiceImpl implements PasienService {
  @Autowired
  private PasienDb pasienDb;

  @Override
  public PasienModel addPasien(PasienModel pasien) {
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
  public PasienModel getPasienByUsername(String username) {
    return pasienDb.findByUsername(username);
  }

  @Override
  public List<PasienModel> getListPasien() {
    return pasienDb.findAll();
  }

}
