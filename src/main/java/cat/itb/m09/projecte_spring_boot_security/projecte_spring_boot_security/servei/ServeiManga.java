package cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.servei;

import cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.RepositoriManga;
import cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.model.Manga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServeiManga {
     @Autowired
    private RepositoriManga repositori  ;

    public void afegir(Manga manga) {
        repositori.save(manga);
    }

    public List<Manga> llistat() {
        List<Manga> actualList = new ArrayList<>();
        repositori.findAll().iterator().forEachRemaining(actualList::add);
        return actualList;
    }


    public Manga consultaMangaPerNom(String s) {
        for (int i = 0; i < repositori.count(); i++) {
            if (s.equals(repositori.findById((long) i).get().getNom())){

                return repositori.findById((long) i).get();
            }
        }
        return null;
    }

    public void eliminatPerNom(String s){
        Manga manga = null;
        boolean encontrado = false;
        for (int i = 0; i < repositori.count() && !encontrado; i++) {
            if (s.equals(repositori.findById((long) i).get().getNom())){
                manga = repositori.findById((long) i).get();
                encontrado=true;
            }
        }
        repositori.delete(manga);

    }
    public void actualitzarPersonatgePerNom(Manga manga, String nombre ){
        for (int i = 0; i < repositori.count(); i++) {
            if (nombre.equals(repositori.findById((long) i).get().getNom())){

                repositori.findById((long) i).get().setNom(manga.getNom());
                repositori.findById((long) i).get().setAutor(manga.getAutor());

            }
        }
    }

    @PostConstruct
    public void init() {
        if (repositori.count()==0) {
            repositori.save(new Manga("BERSERK", "kintaro Miura"));
            repositori.save(new Manga("The Promised Neverland", "Kaiu Shirai"));
            repositori.save(new Manga("Blame!", "Tsutomu Nihei"));
        }
    }
}
