package com.example.tp2_paint.forme;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Triangle extends Forme {

    private Path path1;
    private Path path2;
    private Path path3;
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

        path1 = new Path();
        path2 = new Path();
        path3 = new Path();
        path1.moveTo(x,y);

    }

    @Override
    public void dessiner(Canvas canvas) {
        this.getCrayon().setStyle((Paint.Style.FILL));

        canvas.drawLine(this.pointX, this.pointY, this.pointX2, this.pointY2, this.getCrayon());
        canvas.drawLine(this.pointX2, this.pointY2, this.pointX3, this.pointY3, this.getCrayon());
//
//        canvas.drawPath(path2, this.getCrayon());
//        canvas.drawPath(path1, this.getCrayon());
//        canvas.drawPath(path3, this.getCrayon());

    }

    @Override
    public void move(float x, float y) {
        pointX2 = x;
        pointY2 = y;
    }

    public void troisiemePoint(float x, float y){
        this.pointX3 = x;
        this.pointY3 = y;
    }

}
