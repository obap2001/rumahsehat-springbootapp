package tk.apap.rumahsehat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tk.apap.rumahsehat.model.AdminModel;
// import tk.apap.rumahsehat.model.UserModel;
import tk.apap.rumahsehat.repository.AdminDb;

@Service
public class AdminServiceImpl implements AdminService {
  @Autowired
  private AdminDb adminDb;

  // @Autowired
  // private UserService userService;

  @Override
  public AdminModel addAdmin(AdminModel admin) {
    String pass = encrypt(admin.getPassword());
    admin.setPassword(pass);

    // Add to UserModel
    // UserModel user = new UserModel();
    // user.setEmail(admin.getEmail());
    // user.setNama(admin.getNama());
    // user.setPassword(pass);
    // user.setUsername(admin.getUsername());
    // user.setIsSso(true);
    // user.setRole("admin");
    // userService.addUser(user);
    return adminDb.save(admin);
  }

  @Override
  public String encrypt(String password) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String hashedPassword = passwordEncoder.encode(password);
    return hashedPassword;
  }

  @Override
  public AdminModel getUserByUsername(String username) {
    return adminDb.findByUsername(username);
  }

  @Override
  public List<AdminModel> getListUser() {
    return adminDb.findAll();
  }

  @Override
  public void deleteUser(AdminModel user) {
    adminDb.delete(user);  
  }

}
