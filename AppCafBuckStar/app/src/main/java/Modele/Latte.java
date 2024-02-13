package Modele;

public class Latte extends Produit {
    public Latte(String format) {
        super("Latté "+format, format, 4.00, 125);
    }

    @Override
    public double getPrix() {

        if(this.getFormat().equals("Moyen")) {
            return super.getPrix();
        }
        if(this.getFormat().equals("Grand")) {
            return super.getPrix()*1.125; //parce que super retourne prix*2.2 donc j'ai ajusté a la main...
        }

        else{
            return super.getPrix();
        }
    }
}
