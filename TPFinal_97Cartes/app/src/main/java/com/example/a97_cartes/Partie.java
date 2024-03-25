package com.example.a97_cartes;

public class Partie {

    //communique avec activity;
    //4 pile, 1 paquet carte, 1 jeu
    private Pile pileCroissante1;
    private Pile pileCroissante2;
    private Pile pileDecroissante1;
    private Pile getPileDecroissante2;

    private PaquetCartes paquet;
    private Jeu jeu;

    public Partie() {
        this.pileCroissante1 = new Pile("croissante", 0);
        this.pileCroissante2 = new Pile("croissante", 0);
        this.pileDecroissante1 = new Pile("decroissante", 98);
        this.getPileDecroissante2 = new Pile("decroissante", 98);
        this.paquet = new PaquetCartes();
        this.jeu = new Jeu(this.paquet);
    }

    public Pile getPileCroissante1() {
        return pileCroissante1;
    }

    public Pile getPileCroissante2() {
        return pileCroissante2;
    }

    public Pile getPileDecroissante1() {
        return pileDecroissante1;
    }

    public Pile getGetPileDecroissante2() {
        return getPileDecroissante2;
    }

    public PaquetCartes getPaquet() {
        return paquet;
    }

    public Jeu getJeu() {
        return jeu;
    }
}
