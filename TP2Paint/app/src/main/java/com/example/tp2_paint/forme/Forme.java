package com.example.tp2_paint.forme;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public abstract class Forme {

    private float largeur;
    private String couleur;
    private Paint crayon;

    public Forme(float largeur, String couleur) {
        this.largeur = largeur;
        this.couleur = couleur;

        this.crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.crayon.setStrokeWidth(largeur);
        this.crayon.setStyle(Paint.Style.STROKE);
        this.crayon.setColor(Color.parseColor(couleur));
    }

    public abstract void dessiner(Canvas canvas);

    public abstract void move(float x, float y);

    public Paint getCrayon() {
        return crayon;
    }

    //a enlever fonctions inutiles

    public float getLargeur() {
        return largeur;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

}
