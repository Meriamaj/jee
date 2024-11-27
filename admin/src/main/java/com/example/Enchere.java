package com.example;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Enchere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private Date date;

    private int montantEnch;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private Pokemon pokemon;
    

    public Enchere() {}
    
    public Enchere(Long id, String nom, Date date, int montantEnch, Utilisateur utilisateur){
        this.id=id;
        this.nom=nom;
        this.date=date;
        this.montantEnch=montantEnch;
        this.utilisateur=utilisateur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMontantEnch() {
        return montantEnch;
    }

    public void setMontantEnch(int montantEnch) {
        this.montantEnch = montantEnch;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }




}
