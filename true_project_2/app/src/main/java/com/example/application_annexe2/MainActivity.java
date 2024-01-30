package com.example.application_annexe2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    //1. initialiser variables globalement

    Ecouteur ec;
    Button boutonValider;
    EditText champNomCompte;
    TextView champSolde;
    EditText receveur;

    EditText sommeTransfert;
    Button boutonEnvoyer;

    int solde;

    Vector <String> v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boutonValider = findViewById(R.id.valider);
        champNomCompte = findViewById(R.id.typeCompte);
        champSolde = findViewById(R.id.solde);
        receveur = findViewById(R.id.receveur);
        sommeTransfert = findViewById(R.id.transfertSomme);
        boutonEnvoyer = findViewById(R.id.envoyer);
        solde = 10000000;
        v = new Vector();
        v.add("CHEQUES");
        v.add("EPARGNE");
        v.add("EPARGNEPLUS");

        //1.2 créer ec (classe en dernier)!
        ec = new Ecouteur();
        //2. inscription de la source a l'ecouter
        boutonValider.setOnClickListener(ec);
        boutonEnvoyer.setOnClickListener(ec);



    }
    //3. definir la classe ecouteur (classe interne ici)
    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View source) { //button here will be passed as parameter as object
            if(source==boutonValider){

                String nom = champNomCompte.getText().toString();
                nom = nom.toUpperCase();


                if(v.contains(nom)){
                    champSolde.setText(Integer.toString(solde));
                }
                else {
                    champSolde.setText("pas le bon compte");
                }

            }
            if(source==boutonEnvoyer){
                String goodreceveur = receveur.getText().toString().trim();
                if(goodreceveur.length()!=0){ //si pas vide
                    String stringsomme = sommeTransfert.getText().toString();
                    int somme = Integer.parseInt(stringsomme);
                    champSolde.setText(Integer.toString(solde-somme));
                }
                else{
                    receveur.setText("Indiquer un destinataire");
                }
            }



        }
    }
}