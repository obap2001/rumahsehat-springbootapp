package tk.apap.rumahsehat.service;

import java.util.List;

import tk.apap.rumahsehat.model.ResepModel;

public interface ResepService {
    void addResep(ResepModel resep);
    List<ResepModel> getListResep();
    void deleteResep(ResepModel resep);
    ResepModel getResepById(Long id);

}
