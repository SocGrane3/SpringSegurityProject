package cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Manga {

    private String nom;
    private String autor;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public Manga(String nom, String videojoc) {
        this.nom = nom;
        this.autor = videojoc;
    }
}
