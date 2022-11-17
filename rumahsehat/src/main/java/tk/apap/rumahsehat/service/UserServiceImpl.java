package tk.apap.rumahsehat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// import tk.apap.rumahsehat.model.AdminModel;
import tk.apap.rumahsehat.model.UserModel;
// import tk.apap.rumahsehat.repository.AdminDb;
import tk.apap.rumahsehat.repository.UserDb;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserDb userDb;

  // @Autowired
  // private AdminDb adminDb;

  @Override
  public UserModel addUser(UserModel user) {
    String pass = encrypt(user.getPassword());
    user.setPassword(pass);
    // if (user.getRole().equals("admin")) {
    //   AdminModel admin = new AdminModel();
    //   admin.setEmail(user.getEmail());
    //   admin.setNama(user.getNama());
    //   admin.setPassword(pass);
    //   admin.setUsername(user.getUsername());
    //   admin.setIsSso(true);
    //   admin.setRole("admin");
    //   adminDb.save(admin);
    // }
    return userDb.save(user);
  }

  @Override
  public String encrypt(String password) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String hashedPassword = passwordEncoder.encode(password);
    return hashedPassword;
  }

  @Override
  public UserModel getUserByUsername(String username) {
    return userDb.findByUsername(username);
  }

  @Override
  public List<UserModel> getListUser() {
    return userDb.findAll();
  }

  @Override
  public void deleteUser(UserModel user) {
    userDb.delete(user);
  }
}
