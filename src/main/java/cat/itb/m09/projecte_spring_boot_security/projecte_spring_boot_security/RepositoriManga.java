package cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security;

import cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.model.Manga;
import org.springframework.data.repository.CrudRepository;

public interface RepositoriManga extends CrudRepository<Manga, Long> {

}
