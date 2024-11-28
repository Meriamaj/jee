package com.example;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class Administrateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Inject
    EntityManager em;

    ArrayList<Utilisateur> mesUtilisateur;
    

    public Administrateur(String name, ArrayList<Utilisateur> mesUtilisateur) {
        this.name = name;
        this.mesUtilisateur = mesUtilisateur;
    }


    public Administrateur() {
    }

    @Transactional
    public Utilisateur createUtilisateur(String name, String password, int limcoins){
        Utilisateur user = new Utilisateur(name,password,limcoins);
        mesUtilisateur.add(user);
        em.persist(user);
        return user;
    }

    public void modifUtilisateur(Utilisateur user, int limcoins){
        user.setLimcoins(limcoins);
    }

    public void deleteUtilisateur(Utilisateur user){
        if (mesUtilisateur != null && mesUtilisateur.contains(user)) {
            mesUtilisateur.remove(user);
            System.out.println("Utilisateur supprimé avec succès : " + user.getNom());
        } else {
            System.out.println("L'utilisateur n'existe pas ou la liste est vide.");
        }
    }

    
}
