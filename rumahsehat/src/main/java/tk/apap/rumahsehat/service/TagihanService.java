package tk.apap.rumahsehat.service;

import java.time.Month;
import java.util.List;
import java.util.HashMap;

import tk.apap.rumahsehat.model.TagihanModel;


public interface TagihanService {
  List<TagihanModel> getListTagihan();
  HashMap<Integer, Integer> mapTanggaltoJumlahTagihanByBulanIni(Month bulan);
  HashMap<Integer, Integer> mapBulanToJumlahTagihanByTahun(int tahun);
  void addTagihan(TagihanModel tagihan);

}
