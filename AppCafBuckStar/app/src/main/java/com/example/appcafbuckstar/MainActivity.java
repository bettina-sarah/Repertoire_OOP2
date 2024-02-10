package com.example.appcafbuckstar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//BUILD SUPPRIME FOLDER POUR LA REMISE
public class MainActivity extends AppCompatActivity {

    ListeProduits liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        liste =  new ListeProduits();
        System.out.println(liste.recupererProduit("Café glacé Petit").getNom());
        // tag de imageview, get it by tag - A FAIRE
    }
}