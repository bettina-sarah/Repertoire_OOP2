package com.example.tp2_paint.forme;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class Efface extends Forme {

    Path path;
    public Efface(float largeur, String couleur, float x, float y) {
        super(largeur, couleur);
        //COULEUR DE L'ECRAN!


        path = new Path();
        path.moveTo(x,y);
    }

    @Override
    public void dessiner(Canvas canvas) {
        canvas.drawPath(path, this.getCrayon());

    }

    public void move(float x, float y) {
        path.lineTo(x,y);
    }
}
