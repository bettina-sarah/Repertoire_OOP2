package com.example.application_annexe2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    //1. initialiser variables globalement

    Ecouteur ec;
    EditText champNomCompte;
    TextView champSolde;
    EditText receveur;

    Spinner spin;

    EditText sommeTransfert;
    Button boutonEnvoyer;

    double solde;

    Hashtable<String, Compte> lesComptes;
    Compte compteChoisi;
    DecimalFormat df;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spin = findViewById(R.id.spinnerNomCompte);
        champSolde = findViewById(R.id.solde);
        receveur = findViewById(R.id.receveur);
        sommeTransfert = findViewById(R.id.transfertSomme);
        boutonEnvoyer = findViewById(R.id.envoyer);

        lesComptes = new Hashtable();
        lesComptes.put("CHEQUES", new Compte("CHEQUES", 600));
        lesComptes.put("EPARGNE", new Compte("EPARGNE", 1000));
        lesComptes.put("EPARGNEPLUS", new Compte("EPARGNEPLUS", 2000));

        Set<String> cles = lesComptes.keySet();
        //transtypage a cause du arrayadapter qui accepte seulement des vector/liste
        Vector<String> choix = new Vector<>(cles);
//        for ( String s : cles){
//            choix.add(s);
//        }

        //1.2 créer ec (classe en dernier)!
        ec = new Ecouteur();
        //2. inscription de la source a l'ecouter
        boutonEnvoyer.setOnClickListener(ec);

        df = new DecimalFormat("0.00$");

        //configurer le spinner (dropdownlist)
        //creer array adapter & linker avec spinner

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, choix);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(ec);

    }
    //3. definir la classe ecouteur (classe interne ici)
    private class Ecouteur implements View.OnClickListener, AdapterView.OnItemSelectedListener {

        @Override
        public void onClick(View source) { //button here will be passed as parameter as object
//                String nom = champNomCompte.getText().toString();
//                nom = nom.toUpperCase();

            if(source==boutonEnvoyer){

                String goodreceveur = receveur.getText().toString().trim();

                String courrielRegex ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

                if(goodreceveur.matches(courrielRegex)){ //si pas vide

                    String stringsomme = sommeTransfert.getText().toString();
                    int sommeTransfert = Integer.parseInt(stringsomme);

                    if(compteChoisi.transferer(sommeTransfert)){
                        champSolde.setText(df.format(compteChoisi.getSolde()));
                    }

                    else{
                        receveur.setText("ERREUR PAS ASSEZ DES FONDS");
                    }
                }
                else{
                    receveur.setText("Indiquer un destinataire");
                }
            }
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //position: 0 , 1, 2 lequel qu'on a select
            TextView textView = (TextView) view;
                    //pcq item des spinner c des textview
            String nomCompte = textView.getText().toString();
           // ou: nomCompte = v.get(position);
            compteChoisi = lesComptes.get(nomCompte);

            champSolde.setText(df.format(compteChoisi.getSolde()));

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}