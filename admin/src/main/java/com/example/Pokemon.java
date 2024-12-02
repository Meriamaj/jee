package com.example;

import java.util.ArrayList;
import java.util.Random;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@ApplicationScoped
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nom;
    private String description;
    private int misePrix;
    private int valReel;
    private int hpStat;
    private int attackStat;
    private int defenseStat;
    private int specialAttackStat;
    private int specialDefenseStat;
    private int speedStat;
    private String imagePoke;
    private ArrayList<Enchere> histoEnchere; 

    @Inject
    EntityManager PokeEntityManager;
    

    public Pokemon() {} 

    public Pokemon(String nom, String description, int valReel, String imagePoke) {
        this.nom = nom;
        this.description = description;
        this.valReel = valReel;
        this.imagePoke = imagePoke;
    }

    public int getHpStat() {
        return hpStat;
    }

    public void setHpStat(int hpStat) {
        this.hpStat = hpStat;
    }

    public int getAttackStat() {
        return attackStat;
    }

    public void setAttackStat(int attackStat) {
        this.attackStat = attackStat;
    }

    public int getDefenseStat() {
        return defenseStat;
    }

    public void setDefenseStat(int defenseStat) {
        this.defenseStat = defenseStat;
    }

    public int getSpecialAttackStat() {
        return specialAttackStat;
    }

    public void setSpecialAttackStat(int specialAttackStat) {
        this.specialAttackStat = specialAttackStat;
    }

    public int getSpecialDefenseStat() {
        return specialDefenseStat;
    }

    public void setSpecialDefenseStat(int specialDefenseStat) {
        this.specialDefenseStat = specialDefenseStat;
    }

    public int getSpeedStat() {
        return speedStat;
    }

    public void setSpeedStat(int speedStat) {
        this.speedStat = speedStat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMisePrix() {
        return misePrix;
    }

    public void setMisePrix(int misePrix) {
        int reel = getValReel();
        int seuilInferieur = (int) (reel * 0.4 );
        int seuilSuperieur = (int) (reel * 1.4 );
        Random random = new Random();
        this.misePrix = random.nextInt(seuilSuperieur - seuilInferieur + 1) + seuilInferieur;;
    }

    public int getValReel() {
        return valReel;
    }

    public void setValReel(int valReel) {
        int hp = getHpStat();
        int attack = getAttackStat();
        int defense = getDefenseStat();
        int speAttack = getSpecialAttackStat();
        int speDefense = getSpecialDefenseStat();
        int speed = getSpeedStat();
        int allStat = hp + attack + defense + speAttack + speDefense+ speed;

        this.valReel = allStat/6;
    }

    public String getImagePoke() {
        return imagePoke;
    }

    public void setImagePoke(String imagePoke) {
        this.imagePoke = imagePoke;
    }

    public ArrayList<Enchere> getHistoEnchere() {
        return histoEnchere;
    }

    public void setHistoEnchere(ArrayList<Enchere> histoEnchere) {
        this.histoEnchere = histoEnchere;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
