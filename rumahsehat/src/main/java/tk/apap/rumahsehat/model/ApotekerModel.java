package tk.apap.rumahsehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "apoteker")
public class ApotekerModel extends UserModel {

    @OneToMany(mappedBy="apoteker", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<ResepModel> listResep;
}
