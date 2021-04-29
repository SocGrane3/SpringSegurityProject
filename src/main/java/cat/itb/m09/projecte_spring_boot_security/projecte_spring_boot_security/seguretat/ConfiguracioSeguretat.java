package cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.seguretat;

import cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.servei.ElMeuUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConfiguracioSeguretat extends WebSecurityConfigurerAdapter {
     @Autowired
     private ElMeuUserDetailsService userDetailsService = new ElMeuUserDetailsService();


    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
               .userDetailsService(userDetailsService)

                .passwordEncoder(passwordEncoder());

        }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/home", "/registration","/error").permitAll()
                .antMatchers( "/update/**","/delete/**").hasRole("ADMIN")
                .antMatchers("/", "/h2-console/**", "/registre","/userList")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll()
                 .and()

                .logout()//redirecció a /login?logout
                .permitAll()

        ;
        http.csrf().disable(); //per h2-console
        http.headers().frameOptions().disable(); //per h2-console



        ;

    }



}
