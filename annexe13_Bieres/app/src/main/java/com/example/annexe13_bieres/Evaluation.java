package com.example.annexe13_bieres;

public class Evaluation {

    //son nom, sa microbrasserie d’origine et son évaluation en étoiles ( 1-5 ).
    private String nom;
    private String microbrasserie;
    private float evaluation;

    public Evaluation(String nom, String microbrasserie, float evaluation) {
        this.nom = nom;
        this.microbrasserie = microbrasserie;
        this.evaluation = evaluation;
    }

    public String getNom() {
        return nom;
    }

    public String getMicrobrasserie() {
        return microbrasserie;
    }

    public float getEvaluation() {
        return evaluation;
    }
}
