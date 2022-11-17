package tk.apap.rumahsehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pasien")
public class PasienModel extends UserModel {
    @NotNull
    @Column(name="saldo", nullable=false)
    @Value("0") 
    private int saldo;

    @NotNull
    @Column(name="umur", nullable=false)
    private int umur;

    @OneToMany(mappedBy="pasien", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<AppointmentModel> listAppointment;

}
