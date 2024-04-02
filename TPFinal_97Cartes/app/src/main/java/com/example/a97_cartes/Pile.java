package com.example.a97_cartes;

public class Pile {

    private String type; //croissante/decroissante
    private int valeurDessus;

    public Pile(String type, int valeurDessus) {
        this.type = type;
        this.valeurDessus = valeurDessus;
    }

    public String getType() {
        return type;
    }

    public int getValeurDessus() {
        return valeurDessus;
    }

    public void setValeurDessus(int valeurDessus) {
        this.valeurDessus = valeurDessus;
    }

    public boolean accepte(int carte){
        if(carte==-1){
            return false;
        }
        if(type.equals("croissante")) {
            if (Math.abs(this.valeurDessus - carte) == 10) {
                return true;
            }
            return this.valeurDessus < carte; //return true
        }
        else if(type.equals("decroissante")){
            if(Math.abs(this.valeurDessus-carte)==10){
                    return true;
            }
            return this.valeurDessus>carte;
        }
        return false;
    }
}
