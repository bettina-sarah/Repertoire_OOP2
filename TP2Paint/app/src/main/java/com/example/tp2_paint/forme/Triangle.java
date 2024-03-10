package com.example.tp2_paint.forme;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Triangle extends Forme {

    Path path;
    private float pointX;
    private float pointY;
    private float pointX2;
    private float pointY2;
    private float pointX3;
    private float pointY3;
    private boolean isTroisiemePoint;

    public Triangle(float largeur, String couleur, float x, float y) {
        super(largeur, couleur);
        //au depart tous les points sont pareil
        this.pointX = x;
        this.pointY = y;
        this.pointX2 = x;
        this.pointY2 = y;
        this.pointX3 = x;
        this.pointY3 = y;

        //1er set de coordonnees
        path = new Path();
        path.moveTo(x,y);
        this.isTroisiemePoint = false;

    }

    @Override
    public void dessiner(Canvas canvas) {
        if(!this.isTroisiemePoint){
            this.getCrayon().setStyle((Paint.Style.FILL_AND_STROKE));
            canvas.drawLine(this.pointX,this.pointY,this.pointX2,this.pointY2,this.getCrayon());
        }
        if(this.isTroisiemePoint){
            canvas.drawPath(this.path, this.getCrayon());
        }
    }

    @Override
    public void move(float x, float y) {
        //pas besoin dans le triangle juste mentionné ici a cause de Forme
    }

    public void deuxiemePoint(float x, float y) {
        pointX2 = x;
        pointY2 = y;
        //2eme set de coordonnees
        path.lineTo(x,y);
    }

    public void troisiemePoint(float x, float y){
        this.pointX3 = x;
        this.pointY3 = y;
        //3eme set
        path.lineTo(x,y);
        this.isTroisiemePoint = true;
    }

}
