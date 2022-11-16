package tk.apap.rumahsehat.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@Entity
@Table(name = "admin")
public class AdminModel extends UserModel {
    
}
