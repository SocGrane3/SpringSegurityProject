package cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.servei;

import cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.model.Usuari;
import cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.servei.ServeiUsuaris;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ElMeuUserDetailsService implements UserDetailsService {


    @Autowired
    private ServeiUsuaris serveiUsuaris;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuari u= serveiUsuaris.consultaPerNom(s);
        User.UserBuilder builder=null;
        if(u!=null){
            builder=User.withUsername(s);
            builder.disabled(false);
            builder.password(u.getPassword());
            builder.roles(u.getRol()); //ADMIN o USER
        }
        else throw new UsernameNotFoundException("Usuari no trobat");
        return builder.build();
    }

}
