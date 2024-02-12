package Modele;

import java.util.Vector;

public class Commande {

    private Vector <Produit> listeBoissons;
    private double total;
    private final double TAXE_TPS = 0.05;
    private final double TAXE_TVQ = 0.09975;


    public Commande() {
        this.listeBoissons = new Vector<>();
        this.total = 0;
    }

    public void ajouterProduit(Produit boisson){
        this.total += boisson.getPrix() + boisson.getPrix()*TAXE_TPS
                + boisson.getPrix()*TAXE_TVQ;
        listeBoissons.add(boisson);
    }

    public Vector<Produit> getListeBoissons() {
        return this.listeBoissons;
    }

    public double getTotal() {
        return this.total;
    }

    public void vider(){
        this.listeBoissons = new Vector<>();
        this.total = 0;
    }
}
