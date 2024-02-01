package com.example.annexe3b_voyage;

import java.util.Vector;

public class Commande {

    public final double TAXE_TVQ = 0.09975;
    public final double TAXE_TPS = 0.05;
    private Vector<Produit> listeCommande;

    public Commande() {
        listeCommande = new Vector();
    }

    public void ajouterProduit(Produit p) {
        listeCommande.add(p);
    }

    public double total() {
        double total = 0;
        for (Produit p:
             listeCommande) {
            total += p.getQte()*p.getPrixUnitaire();
        }
        return total;
    }

    public double taxes() {
        double taxes = TAXE_TVQ*total() + TAXE_TPS*total();
        return taxes;
    }

    public double grandTotal() {

        double grTotal = total() + taxes();

        return grTotal;


    }
}
