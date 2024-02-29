package com.example.tp2_paint.forme;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class Cercle extends Forme {
    private float pointX;
    private float pointY;

    private float pointX2;
    private float pointY2;
    private float rayon;

    public Cercle(float largeur, String couleur, float x, float y) {
        super(largeur, couleur);
        this.pointX = x;
        this.pointY = y;
        this.pointX2 = x;
        this.pointY2 = y;
    }

    @Override
    public void dessiner(Canvas canvas) {
        this.getCrayon().setStyle((Paint.Style.FILL));
        this.rayon = calculerRadius();
        canvas.drawCircle(this.pointX,this.pointY,this.rayon,this.getCrayon());
    }

    @Override
    public void move(float x, float y) {
        pointX2 = x;
        pointY2 = y;
    }

    private float calculerRadius() {
        double rayon = Math.sqrt(Math.pow(this.pointX - this.pointX2, 2) + Math.pow(this.pointY- this.pointY2, 2));
        return (float)rayon;
    }
}
