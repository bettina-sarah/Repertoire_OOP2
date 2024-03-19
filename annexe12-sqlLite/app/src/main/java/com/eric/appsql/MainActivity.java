package com.eric.appsql;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Vector;


public class MainActivity extends AppCompatActivity {
    ListView liste;
    TextView texteReponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        liste = findViewById(R.id.listView);
        texteReponse = findViewById(R.id.texteReponse);

        //chcercher methode

        //! pas this; contexte static et this represente un objet, donc getapplcontext
        GestionBD instance = GestionBD.getInstance(getApplicationContext());
        instance.ouvrirBD();
        Vector<String> v = instance.retournerNomsInventions(); //retourne vect

        //remplir listview comme un spinner
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, v);
        liste.setAdapter(adapter);



    }





}