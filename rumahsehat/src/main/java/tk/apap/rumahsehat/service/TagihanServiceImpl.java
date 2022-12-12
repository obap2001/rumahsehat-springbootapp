package tk.apap.rumahsehat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.apap.rumahsehat.model.TagihanModel;
import tk.apap.rumahsehat.repository.TagihanDb;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.HashMap;

@Service
@Transactional
public class TagihanServiceImpl implements TagihanService{
    @Autowired
    private TagihanDb tagihanDb;

    @Override
    public List<TagihanModel> getListTagihan() {
        return tagihanDb.findAll();
    }

    @Override
    public HashMap<Integer, Integer> mapTanggaltoJumlahTagihanByBulanIni(Month bulan) {
        HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();
        for (TagihanModel tagihan : getListTagihan()){
            if (tagihan.getTanggalTerbuat().getMonth().equals(bulan)) {
                int tanggal = tagihan.getTanggalTerbuat().getDayOfMonth();
                if (result.containsKey(tanggal)) {
                    result.put(tanggal, result.get(tanggal)+1);
                } else {
                    result.put(tanggal, 1);
                }
            }
        }

        int jumlahHari = LocalDate.now().lengthOfMonth();
        for (int i=1; i<jumlahHari; i++) {
            if (!result.containsKey(i)) {
                result.put(i, 0);
            }
        }
    
        return result;
    }

    @Override
    public HashMap<Integer, Integer> mapBulanToJumlahTagihanByTahun(int tahun) {
        HashMap<Integer, Integer> result = new HashMap<>();
        for (TagihanModel tagihan : getListTagihan()) {
            if (tagihan.getTanggalTerbuat().getYear() == tahun) {
                int bulan = tagihan.getTanggalTerbuat().getMonthValue();

                if (result.containsKey(bulan)) {
                    result.put(bulan, result.get(bulan));
                }
                else {
                    result.put(bulan, 1);
                }
            }
        }

        int jumlahBulan = LocalDate.now().lengthOfYear();
        for (int i = 1; i < jumlahBulan; i++) {
            if (!result.containsKey(i)) {
                result.put(i, 0);
            }
        }

        return result;
    }

    @Override
    public void addTagihan(TagihanModel tagihan) {
        // TODO Auto-generated method stub
        
    }
}
