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
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    //1. initialiser variables globalement

    Ecouteur ec;
    EditText champNomCompte;
    TextView champSolde;
    EditText receveur;

    Spinner spin;

    EditText sommeTransfert;
    Button boutonEnvoyer;

    int solde;

    Vector <String> v;
    DecimalFormat df;

    String nomCompte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spin = findViewById(R.id.spinnerNomCompte);
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
        boutonEnvoyer.setOnClickListener(ec);

        df = new DecimalFormat("0.00$");

        //configurer le spinner (dropdownlist)
        //creer array adapter & linker avec spinner

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                v);
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

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //position: 0 , 1, 2 lequel qu'on a select
            TextView textView = (TextView) view;
                    //pcw item des spinner c des textview
            nomCompte = textView.getText().toString();
            //ou: nomCompte = v.get(position);


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}