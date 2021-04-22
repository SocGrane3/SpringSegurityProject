package cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.servei;

 import cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.RepositoriPersonatges;
 import cat.itb.m09.projecte_spring_boot_security.projecte_spring_boot_security.model.Personatge;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
 import javax.persistence.Id;
 import java.util.*;
 import java.util.function.Consumer;

@Service
public class ServeiPersonatges {
     @Autowired
    private RepositoriPersonatges repositori  ;

    public void afegir(Personatge e) {
        repositori.save(e);
    }
    public List<Personatge> llistat() {
        List<Personatge> actualList = new ArrayList<>();
        repositori.findAll().iterator().forEachRemaining(actualList::add);
        return actualList;
    }

    @PostConstruct
    public void init() {
        if (repositori.count()==0) {
            repositori.save(new Personatge("Ragna", "Blazblue"));
            repositori.save(new Personatge("Terry", "Fatal Fury"));
            repositori.save(new Personatge("Jin", "Tekken"));
        }
       // ordenarPerVideojoc();
    }


    public Personatge consultaPerNom(String s) {
        Personatge u = null;
        boolean encontrado = false;
        for (int i = 0; i < repositori.count() && !encontrado; i++) {
            if (s.equals(repositori.findById((long) i).get().getNom())){

                u = repositori.findById((long) i).get();
                encontrado=true;
            }
        }
        return  u;
    }/*
    public void ordenarPerNom() {
        Collections.sort(repositori, new Comparator<Personatge>() {
            @Override
            public int compare(Personatge o1, Personatge o2) {

                return  o1.getNom().toLowerCase(Locale.ROOT).compareTo(o2.getNom().toLowerCase(Locale.ROOT));
            }
        });

    }
    public void ordenarPerVideojoc() {
        Collections.sort(repositori, new Comparator<Personatge>() {
            @Override
            public int compare(Personatge o1, Personatge o2) {

                return  o1.getVideojoc().compareTo(o2.getVideojoc()) ;
            }
        });

    }
*/
    public void eliminatPersontagePerNom(String s){
        Personatge u = null;
        boolean encontrado = false;
        for (int i = 0; i < repositori.count() && !encontrado; i++) {
            if (s.equals(repositori.findById((long) i).get().getNom())){

                u = repositori.findById((long) i).get();
                encontrado=true;
            }
        }
        repositori.delete(u);
  //      ordenarPerNom();

    }

    public void actualitzarPersonatgePerNom(Personatge e, String nombre ){
        Personatge u = null;
        boolean encontrado = false;
        for (int i = 0; i < repositori.count() && !encontrado; i++) {
            if (nombre.equals(repositori.findById((long) i).get().getNom())){


                repositori.findById((long) i).get().setNom(e.getNom());
                repositori.findById((long) i).get().setVideojoc(e.getVideojoc());

            }
        }
    //    ordenarPerNom();

    }






}
