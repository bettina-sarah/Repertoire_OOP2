package com.example.a97_cartes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    GestionDB instance;
    Vector<String> scores;

    TextView meilleurScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meilleurScore = findViewById(R.id.meilleurScore);
        String meilleur = "";

        instance = GestionDB.getInstance(getApplicationContext());
        instance.ouvrirBD();

        Partie partie = new Partie();
        instance.addScore(partie.getScore());
        partie.setScoreTest(4000);
        instance.addScore(partie.getScore());
        instance.addScore(40);
        instance.addScore(50);
        instance.addScore(100);


        if(instance.getMeilleursScores().size()>0){
            scores = instance.getMeilleursScores();
            meilleur = scores.firstElement();
        }

        meilleurScore.setText(meilleur);


    }

    //lier des activity: Intent dans le sens positif
    public void clic(View source){

        Intent i1 = new Intent(this, JeuActivity.class);
        //i.putExtra("nom", "Bettina"); //gettext...
        startActivity(i1);


    }

    //pu de move possible: shift a troisieme activity
}