package tk.apap.rumahsehat.service;

import java.util.List;

import tk.apap.rumahsehat.model.UserModel;


public interface UserService {
  UserModel addUser(UserModel user);
  public String encrypt(String password);
  UserModel getUserByUsername(String username);
  List<UserModel> getListUser();
  void deleteUser(UserModel user);
}
