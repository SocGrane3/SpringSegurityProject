package cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.controlador;

import cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.model.Usuari;
import cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.servei.ServeiUsuaris;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class    ControladorLogin {


    @Autowired
    private ServeiUsuaris servei;

    @GetMapping("/userList")
    public String llistar(Model m){
        m.addAttribute("llistaUsuaris",servei.llistat());
        return "llistatUsuaris";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        model.addAttribute("usuari", new Usuari());
        return "register";
    }

    @PostMapping("/registration")
    public String afegirSubmit(@ModelAttribute("usuari") Usuari e){
        e.setRol("USER");
        servei.afegir(e);
        return "redirect:/";

    }
}
