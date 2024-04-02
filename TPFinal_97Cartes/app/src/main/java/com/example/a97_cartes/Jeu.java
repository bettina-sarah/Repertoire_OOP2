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
    public Carte[][] getJeuCartes() {
        return this.jeuCartes;
    }

    public void enleverCarte(int carteValeur){
        outerLoop: // Label pour boucle exterieure
        for(int i = 0; i<2; i++){
            for(int j=0; j<4; j++)
                if (this.jeuCartes[i][j].getValeur() == carteValeur) { //si objet null ca essaie de comparer avec rien
                    this.jeuCartes[i][j].setValeur(-1);
                    break outerLoop; //exit la boucle tt de suite
                }
        }
    }

    public void rajouterCarte(int carteAjouter){
        outerLoop:
        for(int i = 0; i<2; i++){
            for(int j=0; j<4; j++){
                if(this.jeuCartes[i][j].getValeur() == -1){
                    this.jeuCartes[i][j].setValeur(carteAjouter);
                    break outerLoop;
                }
            }
        }
    }

}
