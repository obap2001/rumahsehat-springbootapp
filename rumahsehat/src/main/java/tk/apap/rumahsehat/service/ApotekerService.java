package tk.apap.rumahsehat.service;

import java.util.List;

import tk.apap.rumahsehat.model.ApotekerModel;


public interface ApotekerService {
  ApotekerModel addApoteker(ApotekerModel apoteker);
  public String encrypt(String password);
  ApotekerModel getApotekerByUsername(String username);
  List<ApotekerModel> getListApoteker();
  // void deleteApoteker(ApotekerModel apoteker);

}
