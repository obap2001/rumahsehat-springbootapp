package tk.apap.rumahsehat.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class JumlahIdModel implements Serializable {
    private static final long serialVersionUID = -3182763128764187L;

    @Size(max = 255)
    @NotNull
    @Column(name = "obat", nullable = false)
    private String obat;

    @NotNull
    @Column(name = "resep", nullable = false)
    private Long resep;
}
