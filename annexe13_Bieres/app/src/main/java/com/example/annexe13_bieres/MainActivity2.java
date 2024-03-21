package com.example.annexe13_bieres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView nomBiere;
    TextView microbrasserie;
    RatingBar ratingBar;
    Button boutonEnregistrer;

    Ecouteur ec;

    Evaluation evalEnregistrer;

    GestionDB instance;

    Intent iMain;
    float evaluation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nomBiere = findViewById(R.id.nomBiere);
        microbrasserie = findViewById(R.id.microbrasserie);
        ratingBar = findViewById(R.id.ratingBar);
        boutonEnregistrer = findViewById(R.id.enregistrer);

        ec = new Ecouteur();

        boutonEnregistrer.setOnClickListener(ec);
        ratingBar.setOnRatingBarChangeListener(ec);

        instance = GestionDB.getInstance(getApplicationContext());
        instance.ouvrirBD();

        iMain = new Intent(MainActivity2.this, MainActivity.class);


    }

    private class Ecouteur implements View.OnClickListener, RatingBar.OnRatingBarChangeListener {

        @Override
        public void onClick(View source) {
            if(source == boutonEnregistrer){



               evalEnregistrer = new Evaluation(nomBiere.getText().toString(),
                       microbrasserie.getText().toString(), evaluation);

                instance.ajouterEvaluation(evalEnregistrer);

                startActivity(iMain);
            }

        }

        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            evaluation = rating;

        }
    }
}