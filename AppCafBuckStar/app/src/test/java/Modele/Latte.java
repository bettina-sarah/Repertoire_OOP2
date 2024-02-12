package Modele;

public class Latte extends Produit {
    public Latte(String format) {
        super("Americano "+format, format, 4.00, 125);
    }

    @Override
    public void setPrix(double prix) {
        if(this.getFormat()=="Moyen"){
            super.setPrix(5/3*this.getPrix());
        }
        else if(this.getFormat()=="Grand"){
            super.setPrix(2.5*this.getPrix());
        }
    }
    @Override
    public void setNbCalories(double nbCalories) {

        if(this.getFormat()=="Moyen"){
            super.setNbCalories(5/3*this.getNbCalories());
        }
        else if(this.getFormat()=="Grand"){
            super.setNbCalories(2*this.getNbCalories());
        }
    }
}
