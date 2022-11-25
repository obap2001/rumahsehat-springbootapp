package tk.apap.rumahsehat.service;

import tk.apap.rumahsehat.model.ObatModel;

import java.util.List;

public interface ObatService {
    List<ObatModel> getListObat();
    ObatModel updateObat(ObatModel obat);
    ObatModel getObatById(String id);
}
