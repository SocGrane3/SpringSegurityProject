package cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security;

import cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.model.Personatge;
import cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.model.Usuari;
import org.springframework.data.repository.CrudRepository;

public interface RepositoriPersonatges extends CrudRepository<Personatge, Long> {

}
