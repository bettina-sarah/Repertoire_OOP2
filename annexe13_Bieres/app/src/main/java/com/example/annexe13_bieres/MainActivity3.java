package com.example.annexe13_bieres;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Vector;

public class MainActivity3 extends AppCompatActivity {

    ListView listeBieres;

    GestionDB instance;

    Vector<String> bieres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        listeBieres = findViewById(R.id.listeBieres);

        instance = GestionDB.getInstance(getApplicationContext());
        instance.ouvrirBD();

        bieres = new Vector<>();
        bieres = instance.get3MeilleuresBieres();

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bieres);
        listeBieres.setAdapter(adapter);



    }
}