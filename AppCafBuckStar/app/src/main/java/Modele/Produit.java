package Modele;

public class Produit {

    private String nom;
    private String format;
    private double prix;
    private double nbCalories;

    public Produit(String nom, String format, double prix, double nbCalories) {
        if(prix>30){

        }
        this.nom = nom;
        this.format = format;
        this.prix = prix;
        this.nbCalories = nbCalories;
    }

    public String getNom() {
        return this.nom;
    }

    public String getFormat() {
        return this.format;
    }

    public double getPrix() {

        if(this.getFormat().equals("Moyen")) {
            return this.prix*5/3;
        }
        if(this.getFormat().equals("Grand")) {
            return this.prix*2.2;
        }
        else{
            return this.prix;
        }
    }

    public double getNbCalories() {

        if(this.getFormat().equals("Moyen")) {
            return this.nbCalories+2;
        }
        if(this.getFormat().equals("Grand")) {
            return this.nbCalories*2;
        }
        else{
            return this.nbCalories;
        }
    }
}
