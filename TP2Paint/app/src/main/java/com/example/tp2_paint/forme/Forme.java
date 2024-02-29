package com.example.tp2_paint.forme;

import android.graphics.Canvas;

public abstract class Forme {

    private float largeur;
    private String couleur;

    public Forme(float largeur, String couleur) {
        this.largeur = largeur;
        this.couleur = couleur;
    }

    public abstract void dessiner(Canvas canvas, float x, float y);

}
