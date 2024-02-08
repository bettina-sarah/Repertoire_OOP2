package com.example.application_annexe2;

public class Compte {
    private String nom;
    private double solde;

    public Compte(String nom, double solde) {
        this.nom = nom;
        this.solde = solde;
    }

    public String getNom() {
        return this.nom;
    }

    public double getSolde() {
        return this.solde;
    }

    public boolean transferer(double montantTransfert){

        if(this.solde >= montantTransfert){
            this.solde -= montantTransfert;
            return true;
        }
        else{
            return false;
        }


    }



}
