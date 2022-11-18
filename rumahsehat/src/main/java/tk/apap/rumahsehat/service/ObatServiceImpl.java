package tk.apap.rumahsehat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.apap.rumahsehat.model.ObatModel;
import tk.apap.rumahsehat.repository.ObatDb;

import java.util.List;

@Service
public class ObatServiceImpl implements ObatService{
    @Autowired
    private ObatDb obatDb;

    @Override
    public List<ObatModel> getListObat(){
        return obatDb.findAll();
    }
}
