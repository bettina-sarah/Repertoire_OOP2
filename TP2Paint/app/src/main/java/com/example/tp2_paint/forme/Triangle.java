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

    }

    @Override
    public void dessiner(Canvas canvas) {
        this.getCrayon().setStyle((Paint.Style.FILL));
        canvas.drawPath(path, this.getCrayon());
    }

    @Override
    public void move(float x, float y) {
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
    }

}
