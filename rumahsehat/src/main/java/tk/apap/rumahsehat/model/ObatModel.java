package tk.apap.rumahsehat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(value={"listJumlah"}, allowSetters = true)
@Table(name = "obat")
public class  ObatModel implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy="uuid")
    @Column(name = "id_obat", nullable = false)
    private String id;

  @NotNull
  @Size(max=50)
  @Column(name = "nama_obat", nullable = false)
  private String nama;

  @NotNull
  @Column(name = "harga", nullable = false)
  private int harga;

  @NotNull
  @Column(name = "stok", nullable = false)
  @Value("100") 
  private int stok;

  @OneToMany(mappedBy = "obat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  List<JumlahModel> listJumlah;

}
