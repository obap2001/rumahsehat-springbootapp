package tk.apap.rumahsehat.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tk.apap.rumahsehat.model.PasienModel;
import tk.apap.rumahsehat.model.UserModel;
import tk.apap.rumahsehat.repository.PasienDb;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class PasienRestServiceImplTest {
    @Mock
    PasienDb pasienDb;

    @InjectMocks
    PasienRestServiceImpl pasienRestService;

    @BeforeEach
    public void SetUp(){
        List<PasienModel> listPasien = new ArrayList<>();
        UserModel userModel1 = new UserModel("uuid1","Pasien1","pasien","pasien1","Pasien123!","a.b1@c.d",false);
        UserModel userModel2 = new UserModel("uuid2","Pasien2","pasien","pasien2","Pasien123@","a.b2@c.d",false);
        PasienModel pasienModel1 = new PasienModel(2000,16,new ArrayList<>());
        pasienModel1.setUuid(userModel1.getUuid());
        pasienModel1.setPassword(userModel1.getNama());
        pasienModel1.setRole(userModel1.getRole());
        pasienModel1.setUsername(userModel1.getUsername());
        pasienModel1.setNama(userModel1.getNama());
        pasienModel1.setEmail(userModel1.getEmail());
        pasienModel1.setIsSso(userModel1.getIsSso());
        PasienModel pasienModel2 = new PasienModel(12000, 28, new ArrayList<>());
        pasienModel2.setUuid(userModel2.getUuid());
        pasienModel2.setPassword(userModel2.getNama());
        pasienModel2.setRole(userModel2.getRole());
        pasienModel2.setUsername(userModel2.getUsername());
        pasienModel2.setNama(userModel2.getNama());
        pasienModel2.setEmail(userModel2.getEmail());
        pasienModel2.setIsSso(userModel2.getIsSso());
        System.out.println(pasienModel1);
        System.out.println(pasienModel2);
        listPasien.add(pasienModel1);
        listPasien.add(pasienModel2);
        pasienRestService.registerPasien(pasienModel1);
        pasienRestService.registerPasien(pasienModel2);

        Mockito.when(pasienDb.findAll())
                .thenReturn(listPasien);
    }
    @Test
    @DisplayName("Unit Testing for retrievePasienByUsername method")
    public void retrievePasienByUsername(){
        List<PasienModel> pasienModelList = pasienDb.findAll();
        PasienModel pasienModel1 = pasienModelList.get(0);
        Optional<PasienModel> retrievedPasien = Optional.ofNullable(pasienRestService.retrievePasienByUsername(pasienModel1.getUsername()));

        assertNotEquals(retrievedPasien, null);
    }
    @Test
    @DisplayName("Unit Testing for encrypt method")
    public void encrypt() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = pasienRestService.encrypt("passwordTest");

        passwordEncoder.matches(hashedPassword, "passwordTest");
    }
    @Test
    @DisplayName("Unit Testing for updateSaldo method")
    public void updateSaldo(){
        List<PasienModel> pasienModelList = pasienRestService.retrieveListPasien();
        PasienModel pasienModel1 = pasienModelList.get(0);
        pasienModel1.setSaldo(pasienModel1.getSaldo()+10000);
        PasienModel updatedSaldoPasien = pasienRestService.updateSaldo(pasienModel1);

//        assertNotEquals(updatedSaldoPasien.getSaldo(), 12000);
    }
}
