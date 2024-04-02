package com.example.a97_cartes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Vector;

public class DBActivity extends AppCompatActivity {
    ListView listeScore;
    GestionDB instance;
    Vector<String> scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbactivity);

        listeScore = findViewById(R.id.listeScore);

        instance = GestionDB.getInstance(getApplicationContext());
        instance.ouvrirBD();

        scores = instance.getMeilleursScores();

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scores);
        listeScore.setAdapter(adapter);


        instance.fermerBD();

    }
}