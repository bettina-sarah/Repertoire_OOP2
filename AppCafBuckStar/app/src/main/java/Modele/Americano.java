package Modele;

public class Americano extends Produit {
    public Americano(String format) {
            super("Americano "+format, format, 2.40, 9);
            if(format.equals("Moyen") || format.equals("Grand")){
                this.setPrix();
                this.setNbCalories();
            }
    }
    public void setPrix() {
        if(this.getFormat()=="Moyen"){
            super.setPrix(5/3*this.getPrix());
        }
        else if(this.getFormat()=="Grand"){
            super.setPrix(2.2*this.getPrix());
        }
    }
    public void setNbCalories() {
        if(this.getFormat()=="Moyen"){
            super.setNbCalories(this.getNbCalories()+2);
        }
        else if(this.getFormat()=="Grand"){
            super.setNbCalories(2*this.getNbCalories());
        }
    }
}
