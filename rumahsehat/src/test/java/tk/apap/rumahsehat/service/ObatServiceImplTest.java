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

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ObatServiceImplTest {
    @Mock
    private ObatDb obatDb;

    @InjectMocks
    ObatServiceImpl obatService;

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
        List<ObatModel> obatModelList = obatService.getListObat();
        assertEquals(2, obatModelList.size());
    }

    @Test
    @DisplayName("Unit Testing for updateObat method")
    public void updateObat(){
        List<ObatModel> obatModelList = obatService.getListObat();
        ObatModel obatModel1 = obatModelList.get(0);

        obatModel1.setNama("Updated Obat Name");
        obatModel1.setStok(100);

        ObatModel updatedObat = obatService.updateObat(obatModel1);

        assertEquals(updatedObat.getStok(), 100);
        assertEquals(updatedObat.getNama(), "Updated Obat Name");
    }

    @Test
    @DisplayName("Unit Testing for getObatById method")
    public void getObatById(){
        List<ObatModel> obatModelList = obatService.getListObat();
        ObatModel obatModel1 = obatModelList.get(0);
        ObatModel savedObat = new ObatModel();

        Optional<ObatModel> retrievedObat = Optional.ofNullable(obatService.getObatById(obatModel1.getId()));
        if(retrievedObat.isPresent())
            savedObat = retrievedObat.get();

        assertNotEquals(savedObat, null);
    }
}
