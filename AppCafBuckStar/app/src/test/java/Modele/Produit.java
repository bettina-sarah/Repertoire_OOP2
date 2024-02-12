package Modele;

public class Produit {

    private String nom;
    private String format;
    private double prix;
    private double nbCalories;

    public Produit(String nom, String format, double prix, double nbCalories) {
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
        return this.prix;
    }

    public double getNbCalories() {
        return this.nbCalories;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setNbCalories(double nbCalories) {
        this.nbCalories = nbCalories;
    }
}
