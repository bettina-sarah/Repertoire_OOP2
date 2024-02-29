package com.example.tp2_paint.forme;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class TraceLibre extends Forme {
    Path path;


    public TraceLibre(float largeur, String couleur, float x, float y) {
        super(largeur, couleur);

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
