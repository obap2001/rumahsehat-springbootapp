package tk.apap.rumahsehat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.apap.rumahsehat.model.TagihanModel;
import tk.apap.rumahsehat.repository.TagihanDb;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class TagihanRestServiceImpl implements TagihanRestService{
    @Autowired
    private TagihanDb tagihanDb;

    @Override
    public List<TagihanModel> retrieveListTagihan() {
        return tagihanDb.findAll();
    }

    @Override
    public TagihanModel getTagihanByKode(String kode) {
        Optional<TagihanModel> tagihan = tagihanDb.findByKode(kode);
        if (tagihan.isPresent()) {
            return tagihan.get();
        } else {
            throw new NoSuchElementException();
        }
    }
}
