package ex.appex2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ItemMinecraft {
    private String typeItem;
    private String couleur;
    private int largeurTrait;

    public ItemMinecraft(String typeItem, String couleur, int largeurTrait) {
        this.typeItem = typeItem;
        this.couleur = couleur;
        this.largeurTrait = largeurTrait;
    }


    public void dessiner(Canvas canvas, Paint crayon){
        crayon.setStrokeWidth(this.largeurTrait);
        crayon.setColor(Color.parseColor(this.couleur));

        if(this.typeItem.equals("Bambou")){
            canvas.drawLine(50,50,450,450,crayon);
        }

        else if(this.typeItem.equals("Cloture")){
            //2 verticale & 1 horizontale
            canvas.drawLine(50,50,450,450,crayon);
            canvas.drawLine(50,50,450,450,crayon);
            canvas.drawLine(50,50,450,450,crayon);

        }
        else if(this.typeItem.equals("Echelle")){
            //2 verticales
            canvas.drawLine(50,50,450,450,crayon);
            canvas.drawLine(50,50,450,450,crayon);
            //plusieurs horizontales ....
            canvas.drawLine(50,50,450,450,crayon);
            canvas.drawLine(50,50,450,450,crayon);
            canvas.drawLine(50,50,450,450,crayon);
            canvas.drawLine(50,50,450,450,crayon);
            canvas.drawLine(50,50,450,450,crayon);
            canvas.drawLine(50,50,450,450,crayon);
            canvas.drawLine(50,50,450,450,crayon);
            canvas.drawLine(50,50,450,450,crayon);

        }





    }
}
