package cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.servei;

import cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.RepositoriUsuaris;
import cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.model.Usuari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
public class ServeiUsuaris {
    @Autowired
    private RepositoriUsuaris repositori;

    public void afegir(Usuari e) {
        e.setPassword(passwordEncoder(e.getPassword()));
        repositori.save(e);
    }

    public List<Usuari> llistat() {
        List<Usuari> actualList = new ArrayList<>();
        repositori.findAll().iterator().forEachRemaining(actualList::add);
        return actualList;
    }

    public Usuari consultaPerId(Long id) {
        return repositori.findById(id).orElse(null);
    }
    public Usuari consultaPerNom(String username) {
        final Usuari[] usuariR = new Usuari[1];
        repositori.findAll().forEach(new Consumer<Usuari>() {
            @Override
            public void accept(Usuari usuari) {
                if (usuari.getUsername().equals(username))
                    usuariR[0] = usuari;
            }
        });
        return usuariR[0];
    }


    @PostConstruct
    public void init() {
        if (repositori.count()==0) {
            repositori.save(new Usuari("1", passwordEncoder("1"), "1", "ADMIN"));
            repositori.save(new Usuari("root", passwordEncoder("toor"), "toor", "ADMIN"));
        }
    }

    public String passwordEncoder(String string) {
        return new BCryptPasswordEncoder().encode(string);
    }
}
