package tk.apap.rumahsehat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;
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
@Table(name = "tagihan")
@JsonIgnoreProperties(value={"appointment"}, allowSetters = true)
public class  TagihanModel implements Serializable {
    // TODO buat kode appointment sesuai ketentuan soal
    // todo change package name for strategy
    // @Id
    // @GeneratedValue(generator = "bill-generator")
    // @GenericGenerator(name = "bill-generator", 
    //   parameters = @Parameter(name = "prefix", value = "BILL"), 
    //   strategy = "tk.apap.rumahsehat.modelgenerator.IdGenerator")
    // private String kode;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "kode", nullable = false)
    private String kode;

    @NotNull
    @Column(name = "tanggal_terbuat", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tanggalTerbuat;

    @NotNull
    @Column(name = "tanggal_bayar", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tanggalBayar;

    @NotNull
    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid;

    @NotNull
    @Column(name = "jumlah_tagihan", nullable = false)
    private int jumlahTagihan;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kode_appointment", referencedColumnName = "kode")
    private AppointmentModel appointment;
}