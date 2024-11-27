package com.example;

import java.util.ArrayList;

public class Administrateur {

    private long id;

    private String name;

    ArrayList<Utilisateur> mesUtilisateur;
    

    public Administrateur(String name, ArrayList<Utilisateur> mesUtilisateur) {
        this.name = name;
        this.mesUtilisateur = mesUtilisateur;
    }


    public Administrateur() {
    }


    public Utilisateur createUtilisateur(String name, String password, int limcoins){
        Utilisateur user = new Utilisateur(name,password,limcoins);
        mesUtilisateur.add(user);
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
