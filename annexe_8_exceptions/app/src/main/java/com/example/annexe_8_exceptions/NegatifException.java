package com.example.annexe_8_exceptions;

public class NegatifException  extends Exception{

    //variable message heritée de Exception
    private double valeurErronee;

    public NegatifException(double valeurErronee){
        //super = exception avec msg
        super("Le montant " + valeurErronee + " est negatif. Recommencez svp");
        this.valeurErronee = valeurErronee;

    }

    public double getValeurErronee() {
        return this.valeurErronee;
    }
}
