package tk.apap.rumahsehat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
//import tk.apap.rumahsehat.Setting.Setting;
import tk.apap.rumahsehat.model.ObatModel;
import tk.apap.rumahsehat.repository.ObatDb;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ObatRestServiceImpl implements ObatRestService{
    @Autowired
    private ObatDb obatDb;

    @Override
    public List<ObatModel> retrieveListObat(){
        return obatDb.findAll();
    }
}
