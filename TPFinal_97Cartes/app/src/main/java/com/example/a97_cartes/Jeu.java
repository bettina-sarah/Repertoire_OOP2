package com.example.a97_cartes;

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

    public void setJeuCartes(Carte[][] jeuCartes) {
        this.jeuCartes = jeuCartes;
    }

    public Carte[][] getJeuCartes() {
        return this.jeuCartes;
    }

    public void enleverCarte(int carteValeur){
        for(int i = 0; i<2; i++){
            for(int j=0; j<4; j++){
                if(this.jeuCartes[i][j].getValeur()==carteValeur){
                    this.jeuCartes[i][j] = null;
                }
            }
        }
    }

    public void ajouterCarte(Carte carteAjouter){
        for(int i = 0; i<2; i++){
            for(int j=0; j<4; j++){
                if(this.jeuCartes[i][j] == null){
                    this.jeuCartes[i][j] = carteAjouter;
                }
            }
        }
    }

}
