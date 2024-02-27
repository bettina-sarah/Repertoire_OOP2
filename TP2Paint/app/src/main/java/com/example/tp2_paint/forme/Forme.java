package com.example.tp2_paint.forme;

import android.graphics.Canvas;

public abstract class Forme {

    private double largeur;
    private String couleur;

    public Forme(double largeur, String couleur) {
        this.largeur = largeur;
        this.couleur = couleur;
    }

     public abstract void dessiner(Canvas canvas);

}
