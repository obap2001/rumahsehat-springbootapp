package tk.apap.rumahsehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "confirmer_uuid", referencedColumnName = "uuid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ApotekerModel apoteker;

    @OneToMany(mappedBy="resep", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<JumlahModel> listJumlah;

    // TODO Relasi dengan Appointment
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kode_appointment", referencedColumnName = "kode")
    private AppointmentModel appointment;

}
