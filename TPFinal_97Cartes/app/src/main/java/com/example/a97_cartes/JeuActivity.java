package com.example.a97_cartes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Chronometer;

import java.util.Vector;

public class JeuActivity extends AppCompatActivity {


    TextView cartesRestantes;
    TextView score;
    Chronometer chrono;
    ImageButton undo;

    LinearLayout jeu1;
    LinearLayout jeu2;
    Ecouteur ec;
    TextView carte1;
    TextView carte2;
    TextView carte3;
    TextView carte4;
    TextView carte5;
    TextView carte6;
    TextView carte7;
    TextView carte8;

    Partie partie;
    LinearLayout parentOrigine;
    LinearLayout nouveauParent;
    View carte;
    View carteJoue;

    GestionDB instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        score = findViewById(R.id.score);
        chrono = findViewById(R.id.chrono);
        cartesRestantes = findViewById(R.id.cartesRestantes);

        LinearLayout[] pilesTab = {findViewById(R.id.pileCroissante1), findViewById(R.id.pileCroissante2),
                findViewById(R.id.pileDecroissante1), findViewById(R.id.pileDecroissante2)};

        undo = findViewById(R.id.undoButton);

        jeu1 = findViewById(R.id.jeu1);
        jeu2 = findViewById(R.id.jeu2);
        ec = new Ecouteur();
        carte1 = findViewById(R.id.carte1);
        carte2 = findViewById(R.id.carte2);
        carte3 = findViewById(R.id.carte3);
        carte4 = findViewById(R.id.carte4);
        carte5 = findViewById(R.id.carte5);
        carte6 = findViewById(R.id.carte6);
        carte7 = findViewById(R.id.carte7);
        carte8 = findViewById(R.id.carte8);

        //créer partie
        partie = new Partie();
        //instance = GestionDB.getInstance(getApplicationContext());
        //instance.ouvrirBD();


        //ondrag listener pour les linear layout, on touch pour les textView
        for (int i = 0; i < pilesTab.length; i++) {
            pilesTab[i].setOnDragListener(ec);
            pilesTab[i].getChildAt(0).setOnTouchListener(ec);
        }

        for (int i = 0; i < jeu1.getChildCount(); i++) {
            LinearLayout temp = ((LinearLayout) jeu1.getChildAt(i));
            temp.setOnDragListener(ec);
            //textview touchlistener & set text en fonction des cartes pigés
            int valeurTempCarte = partie.getJeu().getJeuCartes()[0][i].getValeur();

            ((TextView) temp.getChildAt(0)).setText(String.valueOf(valeurTempCarte));


            temp.getChildAt(0).setOnTouchListener(ec);
        }
        for (int i = 0; i < jeu2.getChildCount(); i++) {
            LinearLayout temp = ((LinearLayout) jeu2.getChildAt(i));
            temp.setOnDragListener(ec);
            //textview touchlistener & set text en fonction des cartes pigés
            int valeurTempCarte = partie.getJeu().getJeuCartes()[1][i].getValeur();
            ((TextView) temp.getChildAt(0)).setText(String.valueOf(valeurTempCarte));

            temp.getChildAt(0).setOnTouchListener(ec);
        }

        undo.setOnClickListener(ec);
        undo.setEnabled(false);

        long elapsedRealtime = SystemClock.elapsedRealtime();
        // Set the time that the count-up timer is in reference to.
        this.chrono.setBase(elapsedRealtime);
        this.chrono.start();


    }


    private class Ecouteur implements View.OnClickListener, View.OnDragListener, View.OnTouchListener {

        @Override
        public boolean onDrag(View source, DragEvent event) { //linear layout
            switch (event.getAction()) {
                //4:drop: get localstate
                case DragEvent.ACTION_DROP:
                    //valider si move est valide en fonction de la pile choisie
                    if (partie.moveEstValide()) {
                        carte = (View) event.getLocalState();
                        //5. get colonne d'origine
                        parentOrigine = (LinearLayout) carte.getParent();
                        //6. enleve carte du jeu2
                        parentOrigine.removeView(carte);
                        //7. placer carte dans pile: new parent & add carte
                        nouveauParent = (LinearLayout) source;
                        // enlever textview de base et remplacer avec carte
                        //nouveauParent.removeAllViews();
                        nouveauParent.addView(carte, 1);
                        carteJoue = carte; // on garde en memoire la carte joué
                        String valeurCarte = ((TextView) carte).getText().toString();
                        String tagPile = nouveauParent.getTag().toString();
                        //enleve carte du jeu[][] et du paquet de cartes
                        //fonctionne pas encore
                        partie.enleverCarte(valeurCarte, tagPile);
                        updateCartesRestantes();
                        updateScore(valeurCarte, tagPile);
                        carte.setVisibility(View.VISIBLE);
                        undo.setEnabled(true);

                    }
                    else{ //move invalide - retourner textView

                        carte.setVisibility(View.VISIBLE);

                    }


                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    carte = (View) event.getLocalState();
                    carte.setVisibility(View.VISIBLE);
                    break;

            }


            return true;
        }

        public void updateCartesRestantes(){
            cartesRestantes.setText(String.valueOf(partie.getCartesRestantes()));
        }
        public void updateScore(String carte, String tagPile){
            partie.updateScore(carte, tagPile);
            score.setText(String.valueOf(partie.getScore()));


        }
        public void ajouterCartes() {
            TextView carteRemplacer = new TextView(JeuActivity.this);
        }



        @Override
        public boolean onTouch(View source, MotionEvent event) {
            //1. shadow builder
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(source);

            //2.: drag & drop du shadow - (localstate) on va chercher dans drag
            source.startDragAndDrop(null, shadowBuilder, source, 0);
            //3. text view a invisible
            source.setVisibility(View.INVISIBLE); //DONNER L'illusion qu'on transporte la carte
            return true;
        }

        @Override
        public void onClick(View source) {
            if(source == undo){
                nouveauParent.removeView(carteJoue);
                parentOrigine.addView(carteJoue);
                //undo de la logique en arriere:
                partie.undo();
                undo.setEnabled(false);
            }
        }
    }
}