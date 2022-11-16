package tk.apap.rumahsehat.service;

import java.util.List;

import tk.apap.rumahsehat.model.AdminModel;


public interface AdminService {
  AdminModel addAdmin(AdminModel admin);
  public String encrypt(String password);
  AdminModel getUserByUsername(String username);
  List<AdminModel> getListUser();
  void deleteUser(AdminModel user);
}
