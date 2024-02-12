package Modele;

public class CafeGlace extends Produit {
    public CafeGlace(String format) {
        super("Café glacé "+format, format, 2.50, 10);
    }

    @Override
    public void setPrix(double prix) {
        if(this.getFormat()=="Moyen"){
            super.setPrix(5/3*this.getPrix());
        }
        else if(this.getFormat()=="Grand"){
            super.setPrix(2.2*this.getPrix());
        }
    }
    @Override
    public void setNbCalories(double nbCalories) {

        if(this.getFormat()=="Moyen"){
            super.setNbCalories(this.getNbCalories()+2);
        }
        else if(this.getFormat()=="Grand"){
            super.setNbCalories(2*this.getNbCalories());
        }
    }
}
