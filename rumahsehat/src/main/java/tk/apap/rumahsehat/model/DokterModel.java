package tk.apap.rumahsehat.model;

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
@Table(name = "dokter")
public class DokterModel extends UserModel {
    
    @NotNull
    @Column(name="tarif", nullable=false)
    private int tarif;

    @OneToMany(mappedBy="dokter", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<AppointmentModel> listAppointment;

}
