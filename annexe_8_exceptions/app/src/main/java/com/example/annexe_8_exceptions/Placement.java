package com.example.annexe_8_exceptions;

public class Placement {
  private double montant;
  private int nbMois;

  private static final double INTERET = 0.024/12;  // 12 mois, taux d interet annuel

      public Placement( double montant, int nbMois ) throws NegatifException  { // indique explicitement qu'une exception peut être lancée
        if(montant<0){
          throw new NegatifException(montant);
          //exception controlée: soit try/catch , ou tu la pousse dans la callstack
          //lancer exception dans modele et capter dans vue.
          //donc try catch dans vue.. donc faut la pousser dans callstack mtn:
        }

        this.montant = montant;
        this.nbMois =   nbMois;
  }

  public double calculerMontantFinal () {
    double total = montant;
    for ( int i = 0; i < nbMois ; i ++ )
      total = total + total * INTERET;
  return total;
  }
  
}