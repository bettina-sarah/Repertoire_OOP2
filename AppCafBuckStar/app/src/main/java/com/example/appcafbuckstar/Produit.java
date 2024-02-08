package com.example.appcafbuckstar;

public class Produit {

    private String nom;
    private String format;
    private double prix;
    private double nbCalories;


    public Produit(String nom, String format, double prix, double nbCalories) {
        this.nom = nom;
        this.format = format;
        this.prix = prix;
        this.nbCalories = nbCalories;
    }
}
