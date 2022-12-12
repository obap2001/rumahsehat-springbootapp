package tk.apap.rumahsehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jumlah")
public class JumlahModel {


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
