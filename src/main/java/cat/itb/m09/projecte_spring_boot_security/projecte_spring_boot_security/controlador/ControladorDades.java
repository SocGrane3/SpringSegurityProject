package cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.controlador;

import cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.model.Personatge;
import cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.servei.ServeiPersonatges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorDades {
    @Autowired
    private ServeiPersonatges serveiPersonatges;
    String nom;

/*
    @GetMapping("/")
    public  String  inici(Model m){
        m.addAttribute("llistaAnimal",serveiAnimal.llistat());
        m.addAttribute("Personatge",new Artist());
        return "home";
    }
*/

    @RequestMapping( value ="/delete/{name}", method = RequestMethod.POST)
    public String eliminarPersonatgePerNom(@PathVariable("name") String nomPersonatge){



        serveiPersonatges.eliminatPersontagePerNom(nomPersonatge);
        return "redirect:/home";
    }

    @RequestMapping("/afegir")
    public String afegirPersonatge(Model model) {
        model.addAttribute("Personatge", new Personatge());
        return "afegirPersonatge";
    }

    @GetMapping("/home")
    public String llistarAnimal(Model m){
        m.addAttribute("llistaPersonatge", serveiPersonatges.llistat());
        m.addAttribute("Personatge",new Personatge());
        return "home";
    }
    @PostMapping("/afegirPersonatge")
    //empleatForm és el nom de l'objecte que es recull al formulari, el CommandObject (bean)
    //https://www.thymeleaf.org/doc/tutorials/2.1/thymeleafspring.html#handling-the-command-object
    public String afegirPersonatgeSubmit(@ModelAttribute("Personatge") Personatge e){
        serveiPersonatges.afegir(e);
        return "redirect:/home";
    }
    @RequestMapping( value ="/update/{name}", method = RequestMethod.POST)
    public String updatePersonatge(@PathVariable("name") String personatge, Model m){

        nom = personatge;
        m.addAttribute("Personatge", serveiPersonatges.consultaPerNom(personatge));

        return "actualitzarPersonatge";
    }

    @PostMapping("/actualitzarPersonatge")
    public String updatePersonatgeSubmit(@ModelAttribute("Personatge") Personatge e){
        serveiPersonatges.actualitzarPersonatgePerNom(e, nom);
        return "redirect:/home";
    }



}
