package tk.apap.rumahsehat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(value={"listAppointment"}, allowSetters = true)
@Table(name = "pasien")
public class PasienModel extends UserModel {
    @NotNull
    @Column(name="saldo", nullable=false, columnDefinition = "int default 0")
    private int saldo;

    @NotNull
    @Column(name="umur", nullable=false)
    private int umur;

    @OneToMany(mappedBy="pasien", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<AppointmentModel> listAppointment;

}
