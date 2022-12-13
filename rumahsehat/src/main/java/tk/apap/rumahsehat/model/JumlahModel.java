package tk.apap.rumahsehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jumlah")
public class JumlahModel {
    private static final long serialVersionUID = 1905122041950251207L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private JumlahIdModel id;

    @MapsId("obat")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "obat", nullable = false)
    private ObatModel obat;

    @MapsId("resep")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resep", nullable = false)
    private ResepModel resep;

    @NotNull
    @Column(name = "kuantitas", nullable = false)
    private int kuantitas;
}
