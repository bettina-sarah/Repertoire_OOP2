package com.example.a97_cartes;

public class Pile {

    private String type; //croissant/decroissant
    private int valeurDessus;

    public Pile(String type, int valeurDessus) {
        this.type = type;
        this.valeurDessus = valeurDessus;
    }

    public boolean accepte(Carte carte){
        if(type.equals("croissant")){
            return this.valeurDessus<carte.getValeur(); //return true
        }
        else if(type.equals("decroissant")){
            return this.valeurDessus>carte.getValeur();
        }
        //regle de 10 a coder ici:
        else if(Math.abs(this.valeurDessus-carte.getValeur())==10){
            return true;
        }
        return false;
    }
}
