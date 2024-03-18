package com.example.a97_cartes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //lier des activity: Intent dans le sens positif
    public void clic(View source){

        //PASSER a la nouvelle activité:
        //Intent . de ou je part a ou je menva
        Intent i = new Intent(this, JeuActivity.class);
        i.putExtra("nom", "Bettina"); //gettext...
        startActivity(i);


    }

    //pu de move possible: shift a troisieme activity
}