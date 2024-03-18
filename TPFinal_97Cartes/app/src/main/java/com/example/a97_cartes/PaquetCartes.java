package com.example.a97_cartes;

import java.util.Vector;

public class PaquetCartes {
    private Vector <Carte> vecteurCartes;

    public PaquetCartes() {
        this.vecteurCartes = new Vector();

        for(int i=0; i<98; i++){
            Carte carte = new Carte(i);
            this.vecteurCartes.add(carte);
        }
    }
}
