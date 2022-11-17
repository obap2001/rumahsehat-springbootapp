package tk.apap.rumahsehat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tk.apap.rumahsehat.model.ApotekerModel;
// import tk.apap.rumahsehat.model.UserModel;
import tk.apap.rumahsehat.repository.ApotekerDb;
// import tk.apap.rumahsehat.repository.UserDb;

@Service
public class ApotekerServiceImpl implements ApotekerService {
  @Autowired
  private ApotekerDb apotekerDb;

  @Override
  public ApotekerModel addApoteker(ApotekerModel apoteker) {
    String pass = encrypt(apoteker.getPassword());
    apoteker.setPassword(pass);
    return apotekerDb.save(apoteker);
  }

  @Override
  public String encrypt(String password) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String hashedPassword = passwordEncoder.encode(password);
    return hashedPassword;
  }

  @Override
  public ApotekerModel getApotekerByUsername(String username) {
    return apotekerDb.findByUsername(username);
  }

  @Override
  public List<ApotekerModel> getListApoteker() {
    return apotekerDb.findAll();
  }

  // @Override
  // public void deleteApoteker(ApotekerModel apoteker) {
  //   apotekerDb.delete(apoteker);
  // }

}
