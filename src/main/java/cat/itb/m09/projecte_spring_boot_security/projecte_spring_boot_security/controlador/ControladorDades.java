package cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.controlador;

import cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.model.Manga;
import cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.servei.ServeiManga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorDades {
    @Autowired
    private ServeiManga serveiManga;
    String nom;

    @RequestMapping( value ="/delete/{name}", method = RequestMethod.POST)
    public String eliminarMangaPerNom(@PathVariable("name") String nomManga){
        serveiManga.eliminatPerNom(nomManga);
        return "redirect:/home";
    }

    @RequestMapping("/afegir")
    public String afegirManga(Model model) {
        model.addAttribute("Manga", new Manga());
        return "afegirManga";
    }

    @GetMapping("/home")
    public String llistarMangas(Model m){
        m.addAttribute("llistaManga", serveiManga.llistat());
        m.addAttribute("Manga",new Manga());
        return "home";
    }

    @PostMapping("/afegirManga")
    public String afegirMangaSubmit(@ModelAttribute("Manga") Manga manga){
        serveiManga.afegir(manga);
        return "redirect:/home";
    }

    @RequestMapping( value ="/update/{name}", method = RequestMethod.POST)
    public String updateManga(@PathVariable("name") String manga, Model m){

        nom = manga;
        m.addAttribute("Manga", serveiManga.consultaMangaPerNom(manga));

        return "modificarManga";
    }

    @PostMapping("/modificarManga")
    public String updateMangaSubmit(@ModelAttribute("Manga") Manga e){
        serveiManga.actualitzarPersonatgePerNom(e, nom);
        return "redirect:/home";
    }
}
