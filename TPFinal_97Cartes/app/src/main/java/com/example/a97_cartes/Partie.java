package com.example.a97_cartes;

public class Partie {

    //communique avec activity;
    //4 pile, 1 paquet carte, 1 jeu
    private Pile pileCroissante1;
    private Pile pileCroissante2;
    private Pile pileDecroissante1;
    private Pile getPileDecroissante2;

    private PaquetCartes paquet;
    private Jeu jeu;

    private int cartesJouees;

    private int score;

    public Partie() {
        this.pileCroissante1 = new Pile("croissante", 0);
        this.pileCroissante2 = new Pile("croissante", 0);
        this.pileDecroissante1 = new Pile("decroissante", 98);
        this.getPileDecroissante2 = new Pile("decroissante", 98);
        this.paquet = new PaquetCartes();
        this.jeu = new Jeu(this.paquet);
        this.cartesJouees = 0;
        this.score = 0;
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

    public Pile getGetPileDecroissante2() {
        return getPileDecroissante2;
    }

    public PaquetCartes getPaquet() {
        return paquet;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public int getScore() {
        return score;
    }

    //a effacer! TEST
    public void setScoreTest(int score) {
        this.score = score;
    }

    public boolean moveEstValide(){
        //a coder - pile avec accepte carte
        return true;
    }

    public void enleverCarte(String carte){
        int intCarte = Integer.parseInt(carte);
        //1. enlever les cartes du PaquetCartes
        this.paquet.enleverCarte(intCarte);
        //2. enlever carte du jeu aussi:
        this.jeu.enleverCarte(intCarte);
        this.cartesJouees++;
        //voir si j'ai besoin de remplacer:
        if(this.cartesJouees==2){
            remplacerCartes();
        }

    }
    public void ajouterCartes(){
        Carte temp = this.paquet.selectCarte();
        this.jeu.ajouterCarte(temp);
    }

    public void remplacerCartes(){
        this.cartesJouees=0;
        //remplace les 2 cartes qui étaient null avant
        for(int i = 0; i<2; i++){
            for(int j=0; j<4; j++){
                if(this.jeu.getJeuCartes()[i][j] == null){
                    this.jeu.getJeuCartes()[i][j] = this.paquet.selectCarte();
                }
            }
        }



    }


}
