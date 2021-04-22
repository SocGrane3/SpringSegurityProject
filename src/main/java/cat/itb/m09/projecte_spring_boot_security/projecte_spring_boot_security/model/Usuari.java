package cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuari {

    private String username;

    private String password;

    private String matchingPassword;

    private String rol;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;


    public Usuari(String name, String password, String matchingPassword) {
        this.username = name;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.rol = "USER";
    }

    public Usuari(String username, String password, String matchingPassword, String rol) {
        this.username = username;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }


}
