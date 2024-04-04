package com.example.a97_cartes;

import java.util.Vector;

public class Partie {
    private Pile pileCroissante1;
    private Pile pileCroissante2;
    private Pile pileDecroissante1;
    private Pile pileDecroissante2;

    private PaquetCartes paquet;
    private Jeu jeu;

    private int cartesJouees;
    private int score;
    private int dernierScore; //pour Undo!
    private int cartesRestantes;
    private int cartePresente; //pour undo savoir quelle carte on vient de jouer
    private String pilePresente; //undo
    private Vector<Integer> tagsRemplacer; //pour l'action de remplacement des 2 cartes

    public Partie() {
        this.pileCroissante1 = new Pile("croissante", 0);
        this.pileCroissante2 = new Pile("croissante", 0);
        this.pileDecroissante1 = new Pile("decroissante", 98);
        this.pileDecroissante2 = new Pile("decroissante", 98);
        this.paquet = new PaquetCartes();
        this.jeu = new Jeu(this.paquet);
        this.cartesJouees = 0;
        this.score = 0;
        this.cartesRestantes = 97;
        this.cartePresente = 0;
        this.pilePresente = "";
        this.tagsRemplacer = new Vector<>();
    }

    public Pile getPileCroissante1() {
        return pileCroissante1;
    }

    public Pile getPileCroissante2() {
        return pileCroissante2;
    }

    public Pile getPileDecroissante1() {
        return pileDecroissante1;
    }

    public Pile getPileDecroissante2() {
        return pileDecroissante2;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public int getScore() {
        return score;
    }


    public boolean moveEstValide(String carte, String pile){
        int intCarte = Integer.parseInt(carte);
        Pile validerPile = retournePile(pile);
        return validerPile.accepte(intCarte);
    }

    public int getCartesRestantes() {
        return cartesRestantes;
    }

    public Vector<Integer> getTagsRemplacer() { //pour remplacement des 2 cartes
        return tagsRemplacer;
    }

    public boolean enleverCarte(String carte, String pile){
        int intCarte = Integer.parseInt(carte);
        //on garde en memoire la carte choisie & pile pour undo
        this.cartePresente = intCarte;
        this.pilePresente = pile;
        //1. enlever carte du jeu: (deja enlevé du paquet)
        this.jeu.enleverCarte(intCarte);
        //2. changer pile correspondante:
        Pile enlever = retournePile(pile);
        enlever.setValeurDessus(intCarte);
        //3. cartes jouées pour savoir quand remplacer & cartes restantes
        this.cartesJouees++;
        this.cartesRestantes--;
        //voir si j'ai besoin de remplacer:
        if(this.cartesJouees==2){
            remplacerCartes();
            return true;
        }
        return false;
    }

    public void remplacerCartes(){
        this.cartesJouees=0;
        //pour savoir quels Linear Layout ont besoin des cartes remplacantes (TextView),
        //on garde en memoire les index qui ont été remplacés (i,j) et seront accessibles par apres
        for(int i = 0; i<2; i++){
            for(int j=0; j<4; j++){
                if(this.jeu.getJeuCartes()[i][j].getValeur() == -1){
                    this.jeu.getJeuCartes()[i][j] = this.paquet.selectCarte();
                    this.tagsRemplacer.add(i);
                    this.tagsRemplacer.add(j);
                    //a la fin, nous allons avoir par ex : "0,1,0,2"
                }
            }
        }
    }

    public void updateScore(String carte, String pile){


        this.dernierScore=10; //score de base
        //1. plus de points accordés si joueur avancé:
        if(this.cartesRestantes >= 33 && this.cartesRestantes < 65){
            this.dernierScore=20;
        }
        else if(this.cartesRestantes < 33){
            this.dernierScore=50;
        }
        //2. move efficace ou non:
        int intCarte = Integer.parseInt(carte);
        Pile pileCourante = retournePile(pile);
        if(Math.abs(pileCourante.getValeurDessus()-intCarte)<5){
            this.dernierScore+=100;
        }

        this.score+=this.dernierScore;
    }


    public void undo() {
        //memes actions qu'enlever carte a l'envers
        this.jeu.rajouterCarte(this.cartePresente);
        Pile pileTemp = retournePile(this.pilePresente);
        pileTemp.setValeurDessus(this.cartePresente);

        this.cartesJouees--;
        this.cartesRestantes++;
        this.score-=this.dernierScore;
    }

    public boolean checkMovesPossibles(){
        for(int i = 0; i<2; i++) {
            for (int j = 0; j < 4; j++) {
                //des qu'il y a un true, retourne true: un move possible!
                int carteTemp = this.jeu.getJeuCartes()[i][j].getValeur();
                if(this.pileCroissante1.accepte(carteTemp)){
                    return this.pileCroissante1.accepte(carteTemp);
                }
                if(this.pileCroissante2.accepte(carteTemp)){
                    return this.pileCroissante2.accepte(carteTemp);
                }
                if(this.pileDecroissante1.accepte(carteTemp)){
                    return this.pileDecroissante1.accepte(carteTemp);
                }
                if(this.pileDecroissante2.accepte(carteTemp)){
                    return this.pileDecroissante2.accepte(carteTemp);
                }

            }
        }
        return false;
    }

    public Pile retournePile(String pile){
        switch(pile){
            case "pileCroissante1":
                return this.pileCroissante1;
            case "pileCroissante2":
                return this.pileCroissante2;
            case "pileDecroissante1":
                return this.pileDecroissante1;
            case "pileDecroissante2":
                return this.pileDecroissante2;
        }
        return null; //si aucune pile trouvé
    }
}
