package com.eric.appsql;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Vector;


public class MainActivity extends AppCompatActivity {
    ListView liste;
    TextView texteReponse;

    Vector<String> v;

    Ecouteur ec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        liste = findViewById(R.id.listView);
        texteReponse = findViewById(R.id.texteReponse);

        //chcercher methode

        //! pas context this; contexte static et this represente un objet, donc getapplcontext
        //singleton fonc pour toute l'appli donc on veut pas donner contexte this cette activité! mais toute lappli!!

        GestionBD instance = GestionBD.getInstance(getApplicationContext());
        instance.ouvrirBD(); //IMPORTANT!
        v = instance.retournerNomsInventions(); //retourne vect

        //remplir listview comme un spinner
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, v);
        //simple list item = textview
        liste.setAdapter(adapter);

        ec = new Ecouteur();

        //! pas setOnItemSelected comme pour le spinner.
        liste.setOnItemClickListener(ec);




    }

    //onStart:
    @Override
    protected void onStop() {
        super.onStop();
        GestionBD instance = GestionBD.getInstance(getApplicationContext());
        instance.fermerAcces();
    }

    private class Ecouteur implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            GestionBD instance = GestionBD.getInstance(getApplicationContext()); //ok de refaire ca... pcq ca la crée pas au nouveau.
            //sinon on peut declarer globalement
            String invention = liste.getItemAtPosition(position).toString(); // tu peux aussi caster
            String invention2 = (String)parent.getSelectedItem(); // parent de param
            //String invention3 = ((TextView)view).getText().toString();
            //String invention4 = v.get(position);

            if(instance.aBonneReponse("Mary Anderson", invention)){
                texteReponse.setText("BRAVO");
                view.setBackgroundColor(Color.GREEN);
            }
            else{
                texteReponse.setText("mauvaise rep");
                view.setBackgroundColor(Color.RED);
                liste.getChildAt(2).setBackgroundColor(Color.GREEN);
            }
        }
    }





}