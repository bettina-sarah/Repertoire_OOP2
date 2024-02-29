package com.example.tp2_paint.forme;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

public class TraceLibre extends Forme {

    Paint crayon;
    Path path;


    public TraceLibre(float largeur, String couleur) {
        super(largeur, couleur);
        crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
        crayon.setStrokeWidth(largeur); //par defaut width de 1
        //A CHANGER largeur de trait
        crayon.setStyle(Paint.Style.FILL);
        crayon.setColor(Color.parseColor(couleur));
        path = new Path();

    }

    @Override
    public void dessiner(Canvas canvas, float x, float y) {

        //start x&y, stop x&y, crayon
        //canvas.drawLine();
        canvas.drawArc(new RectF(),20,70,true,crayon);

        //avec this.couleur ... this.largeur ...
        //path.moveTo();
        //path.lineTo();
        canvas.drawPath(path, crayon);

    }


}
