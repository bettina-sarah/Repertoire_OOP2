package ex.appex2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ItemMinecraft {
    private String typeItem;
    private String couleur;
    private int largeurTrait;
    private Paint crayon;

    public ItemMinecraft(String typeItem, String couleur, int largeurTrait) {
        this.typeItem = typeItem;
        this.couleur = couleur;
        this.largeurTrait = largeurTrait;

        this.crayon = new Paint();
        this.crayon.setStrokeWidth(largeurTrait);
        this.crayon.setStyle(Paint.Style.STROKE);
        this.crayon.setColor(Color.parseColor(couleur));
    }

    public void dessiner(Canvas canvas){
        //canvas.drawLine(this.crayon);

    }
}
