package com.example.projetadressage;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;


import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Collection;
import java.util.Set;
import java.util.Vector;

import bla.HashtableAssociation;


public class MainActivity extends AppCompatActivity {

    EditText champPrenom, champNom, champAdresse, champZip;
    Spinner spinnerCapitale, spinnerEtat;
    HashtableAssociation liste;
    Button bouton;

    Ecouteur ec;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        champPrenom = findViewById(R.id.champPrenom);
        champNom= findViewById(R.id.champNom);
        champAdresse = findViewById(R.id.champAdresse);
        champZip = findViewById(R.id.champZip);

        spinnerCapitale = findViewById(R.id.spinnerCapitale);
        spinnerEtat = findViewById(R.id.spinnerEtat);

        bouton = findViewById(R.id.boutonInscrire);

        ec = new Ecouteur();


        // remplir les spinner à l'aide de la Hashtable

        liste = new HashtableAssociation();

        //liste des clés (keyset):
        Set<String> ensCles = liste.keySet();
        //values coll de string:
        Collection<String> ensEtats = liste.values();

        ArrayAdapter capitales = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                new Vector(ensCles));
        ArrayAdapter etats = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                new Vector(ensEtats));
        spinnerCapitale.setAdapter(capitales);
        spinnerEtat.setAdapter(etats);

        bouton.setOnClickListener(ec);

    }
    private class Ecouteur implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

            //try catch lancer exception

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            try {
                Inscrit i = new Inscrit(champNom.getText().toString(), champPrenom.getText().toString(),
                champAdresse.getText().toString(),(String)spinnerCapitale.getSelectedItem().toString(),
                        (String)spinnerEtat.getSelectedItem().toString(), champZip.getText().toString());
                builder.setMessage("Vous etes inscrit");
                builder.setTitle("Inscription");
            }

            catch (AdresseException ae){
                builder.setMessage(ae.getMessage());
                builder.setTitle("Inscription");
            }

            finally {
                AlertDialog dialog = builder.create();
                dialog.show();
            }


        }
    }
}