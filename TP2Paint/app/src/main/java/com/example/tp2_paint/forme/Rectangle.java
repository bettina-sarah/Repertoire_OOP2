package com.example.tp2_paint.forme;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Rectangle extends Forme {

    private float pointX;
    private float pointY;
    private float pointX2;
    private float pointY2;
    public Rectangle(float largeur, String couleur, float x, float y) {
        super(largeur, couleur);
        this.pointX = x;
        this.pointY = y;
        this.pointX2 = x;
        this.pointY2 = y;
    }

    @Override
    public void dessiner(Canvas canvas) {
        this.getCrayon().setStyle((Paint.Style.FILL));
        canvas.drawRect(this.pointX, this.pointY, this.pointX2, this.pointY2, this.getCrayon());


    }

    @Override
    public void move(float x, float y) {
        pointX2 = x;
        pointY2 = y;

    }
}
