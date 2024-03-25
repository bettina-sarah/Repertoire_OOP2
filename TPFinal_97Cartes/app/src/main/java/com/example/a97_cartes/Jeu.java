package com.example.a97_cartes;

import java.lang.reflect.Array;

public class Jeu {

    private Carte[][] jeuCartes;

    public Jeu(PaquetCartes paquet) {
        this.jeuCartes = new Carte[2][4]; //8 total

        for(int i = 0; i<2; i++){
            for(int j=0; j<4; j++){
                this.jeuCartes[i][j] = paquet.selectCarte();
            }
        }
    }

    public Carte[][] getJeuCartes() {
        return this.jeuCartes;
    }
}
