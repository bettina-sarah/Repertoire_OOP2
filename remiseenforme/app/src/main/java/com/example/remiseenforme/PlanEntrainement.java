package com.example.remiseenforme;

import java.util.Vector;

public class PlanEntrainement {
    Vector<Exercice> ensembleExercices;
    int objectifMinutes;

    public PlanEntrainement(int objectifMinutes) {
        this.ensembleExercices = new Vector<>();
        this.objectifMinutes = objectifMinutes;
    }

    public void ajouterExercice(Exercice exerciceAjouter){
        this.ensembleExercices.add(exerciceAjouter);
    }

    public int calculerTotal(){
        int total = 0;
        for(int i=0; i<this.ensembleExercices.size(); i++){
            total += this.ensembleExercices.elementAt(i).getDureeMinutes();
        }
        return total;
    }

    public boolean isObjectifAtteint(){
        return objectifMinutes <= this.calculerTotal();
    }

    public Vector<Exercice> getEnsembleExercices() {
        return this.ensembleExercices;
    }

    public int getObjectifMinutes() {
        return objectifMinutes;
    }
}
