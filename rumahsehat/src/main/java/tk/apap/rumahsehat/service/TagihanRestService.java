package tk.apap.rumahsehat.service;

import tk.apap.rumahsehat.model.TagihanModel;
import java.util.List;

public interface TagihanRestService {
    List<TagihanModel> retrieveListTagihan();
    TagihanModel getTagihanByKode(String code);
}
