package com.example.remiseenforme;

public class Exercice {
    private String type;
    int dureeMinutes;

    public Exercice(String type, int dureeMinutes) {
        this.type = type;
        this.dureeMinutes = dureeMinutes;
    }

    public String getType() {
        return this.type;
    }
    public int getDureeMinutes() {
        return this.dureeMinutes;
    }


}
