package main.java.com.example;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;

    private String nom;

    private String password ;

    private int limcoins = 1000;


    private List <Enchere> Encheres = new ArrayList<>() ;



    public Utilisateur(){}
    public Utilisateur(Long id, String nom, String password, int Limcoins) {
        this.id=id;
        this.nom=nom;
        this.password=password;
        this.limcoins=limcoins;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLimcoins() {
        return limcoins;
    }

    public void setLimcoins(int limcoins) {
        this.limcoins = limcoins;
    }

    public List<Enchere> getEncheres() {
        return Encheres;
    }

    public void setEncheres(List<Enchere> Encheres) {
        this.Encheres = Encheres;
    }


    public void ajouterLimcoins(int montantLim) {
        if (montantLim>0) {
            this.limcoins = limcoins + montantLim;
        }
    }

    public boolean retirerLimcoins(int montant) {
        if (montant>0 && this.limcoins >= montant) {
            this.limcoins=limcoins-montant;
            return true;
        }
        return false; 
    }

    public boolean Encherir(int montant) {
        return this.limcoins >= montant ;
    }

    

}