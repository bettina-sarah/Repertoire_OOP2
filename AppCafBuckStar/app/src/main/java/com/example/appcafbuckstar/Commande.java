package com.example.appcafbuckstar;

import java.util.Vector;

public class Commande {

    private Vector <Produit> listeBoissons;
    private double total;

    public Commande() {
        this.listeBoissons = new Vector<>();
        this.total = 0;
    }

    public void ajouterProduit(Produit boisson){
        this.total += boisson.getPrix();
        listeBoissons.add(boisson);
    }

    public Vector<Produit> getListeBoissons() {
        return this.listeBoissons;
    }

    public double getTotal() {
        return this.total;
    }
}
