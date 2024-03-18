package com.example.a97_cartes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class JeuActivity extends AppCompatActivity {

    TextView texte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        texte = findViewById(R.id.jeu1);
        texte.setText(getIntent().getStringExtra("nom"));
    }
}