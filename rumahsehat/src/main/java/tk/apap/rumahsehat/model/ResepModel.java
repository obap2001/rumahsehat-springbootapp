package tk.apap.rumahsehat.model;

import lombok.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resep")
public class  ResepModel implements Serializable {
    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resep", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "is_done", nullable = false,  columnDefinition = "boolean default false")
    private Boolean isDone;

    @NotNull
    @Column(name = "created_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createdAt;

    // TODO Relasi dengan Apoteker
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "confirmer_uuid", referencedColumnName = "uuid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ApotekerModel apoteker;

    // TODO Relasi dengan Appointment
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kode_appointment")//, referencedColumnName = "kode")
    private AppointmentModel appointment;

//    // TODO Relasi dengan Obat
//    @OneToMany(mappedBy = "resep", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    List<ObatModel> listObat;

    // TODO Relasi dengan Jumlah
    @OneToMany(mappedBy="resep", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    List<JumlahModel> listJumlah;
}
