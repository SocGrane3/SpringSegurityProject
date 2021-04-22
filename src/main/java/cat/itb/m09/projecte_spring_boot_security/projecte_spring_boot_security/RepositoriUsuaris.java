package cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security;

import cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.model.Usuari;
import org.springframework.data.repository.CrudRepository;

public interface RepositoriUsuaris extends CrudRepository<Usuari,String> {
}
