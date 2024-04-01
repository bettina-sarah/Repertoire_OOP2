package com.example.a97_cartes;

public class Partie {

    //communique avec activity;
    //4 pile, 1 paquet carte, 1 jeu
    private Pile pileCroissante1;
    private Pile pileCroissante2;
    private Pile pileDecroissante1;
    private Pile pileDecroissante2;

    private PaquetCartes paquet;
    private Jeu jeu;

    private int cartesJouees;
    private int score;
    private int cartesRestantes;
    private int cartePresente; //pour undo savoir quelle carte on vient de jouer
    private String pilePresente; //undo

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

    public int getCartesRestantes() {
        return cartesRestantes;
    }

    public void enleverCarte(String carte, String pile){
        int intCarte = Integer.parseInt(carte);
        //on garde en memoire la carte choisie & pile pour undo
        this.cartePresente = intCarte;
        this.pilePresente = pile;
        //1. enlever carte du jeu: (deja enlevé du paquet)
        this.jeu.enleverCarte(intCarte);
        //2. changer pile correspondante:
        switch(pile){
            case "pileCroissante1":
                this.pileCroissante1.setValeurDessus(intCarte);
                break;
            case "pileCroissante2":
                this.pileCroissante2.setValeurDessus(intCarte);
                break;
            case "pileDecroissante1":
                this.pileDecroissante1.setValeurDessus(intCarte);
                break;
            case "pileDecroissante2":
                this.pileDecroissante2.setValeurDessus(intCarte);
                break;
        }
        //3. cartes jouées pour savoir quand remplacer & cartes restantes
        this.cartesJouees++;
        this.cartesRestantes--;
        //voir si j'ai besoin de remplacer:
        if(this.cartesJouees==2){
            remplacerCartes();
        }

    }

    public void remplacerCartes(){
        this.cartesJouees=0;
        //remplace les 2 cartes qui étaient null avant
        for(int i = 0; i<2; i++){
            for(int j=0; j<4; j++){
                if(this.jeu.getJeuCartes()[i][j].getValeur() == -1){
                    this.jeu.getJeuCartes()[i][j] = this.paquet.selectCarte();
                }
            }
        }
    }

    public void updateScore(String carte, String pile){
        //logique pour attribuer un score specifique:
//        Meilleur move si 5 sur 4 au lieu de 20 sur 4 (plus efficace…)
//        La proximité de la carte jouée de la carte sur la suite
//        La vitesse avec laquelle on a joué la carte
        //1. plus de points accordés si joueur avancé:
        if(this.cartesRestantes >= 65){
            this.score+=10;
        }
        else if(this.cartesRestantes >= 33 && this.cartesRestantes < 65){
            this.score+=20;
        }
        else if(this.cartesRestantes < 33){
            this.score+=30;
        }
    }


    public void undo() {
        //memes actions qu'enlever carte a l'envers
        this.jeu.rajouterCarte(this.cartePresente);
        switch(this.pilePresente){
            case "pileCroissante1":
                this.pileCroissante1.setValeurDessus(this.cartePresente);
                break;
            case "pileCroissante2":
                this.pileCroissante2.setValeurDessus(this.cartePresente);
                break;
            case "pileDecroissante1":
                this.pileDecroissante1.setValeurDessus(this.cartePresente);
                break;
            case "pileDecroissante2":
                this.pileDecroissante2.setValeurDessus(this.cartePresente);
                break;
        }
            this.cartesJouees--;
            this.cartesRestantes++;
    }
}
