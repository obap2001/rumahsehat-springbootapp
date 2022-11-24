package tk.apap.rumahsehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointment")
public class AppointmentModel implements Serializable {
     // TODO buat kode appointment sesuai ketentuan soal
     // todo change package name for strategy
    // @Id
    // @GeneratedValue(generator = "apt-generator")
    // @GenericGenerator(name = "apt-generator", 
    //   parameters = @Parameter(name = "prefix", value = "APT"), 
    //   strategy = "tk.apap.rumahsehat.model.generator.IdGenerator")
    // private String kode;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String kode;

    @Column(name = "is_done", nullable = false,  columnDefinition = "boolean default false")
    private Boolean isDone;

    @NotNull
    @Column(name = "waktu_awal", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime waktuAwal;

     // Relasi dengan Pasien
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "uuid_pasien", referencedColumnName = "uuid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PasienModel pasien;
  
    // Relasi dengan Dokter
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "uuid_dokter", referencedColumnName = "uuid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DokterModel dokter;

    // Relasi dengan Tagihan
    @OneToOne(mappedBy = "appointment")
    private TagihanModel tagihan;

    // TODO Relasi dengan Resep
    @OneToOne(mappedBy = "appointment")
    private ResepModel resep;
}

