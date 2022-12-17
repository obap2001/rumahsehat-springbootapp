package tk.apap.rumahsehat.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tk.apap.rumahsehat.model.ObatModel;
import tk.apap.rumahsehat.repository.ObatDb;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ObatRestServiceImplTest {
    @Mock
    private ObatDb obatDb;

    @InjectMocks
    ObatServiceImpl obatRestService;

    @BeforeEach
    public void SetUp(){
        List<ObatModel> listObat = new ArrayList<>();

        ObatModel obatModel1 = new ObatModel("id1", "ObatTest1", 12000, 20, new ArrayList<>());
        ObatModel obatModel2 = new ObatModel("id2", "ObatTest2", 13000, 15, new ArrayList<>());

        listObat.add(obatModel1);
        listObat.add(obatModel2);

        Mockito.when(obatDb.findAll())
                .thenReturn(listObat);

    }

    @Test
    @DisplayName("Unit Testing for getListObat method")
    public void getListObat() {
        List<ObatModel> obatModelList = obatRestService.getListObat();
        assertEquals(2, obatModelList.size());
    }
}
