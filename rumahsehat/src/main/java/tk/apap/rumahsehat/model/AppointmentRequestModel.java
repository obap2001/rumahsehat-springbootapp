package tk.apap.rumahsehat.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

public class AppointmentRequestModel {
    private String kode;
    private Boolean isDone;
    private LocalDateTime waktuAwal;
    private PasienModel pasien;
    private DokterModel dokter;
    private TagihanModel tagihan;
    private ResepModel resep;
}
