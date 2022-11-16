package tk.apap.rumahsehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class JumlahModel implements Serializable {
  @Id
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "id_obat", referencedColumnName = "id_obat")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private ObatModel obat;

  @Id
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "id_resep", referencedColumnName = "id_resep")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private ResepModel resep;

   @NotNull
   @Column(name = "kuantitas", nullable = false)
   private int kuantitas;
}
