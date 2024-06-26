package com.example.a97_cartes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Chronometer;

public class JeuActivity extends AppCompatActivity {


    TextView cartesRestantes;
    TextView score;
    Chronometer chrono;
    ImageButton undo;

    LinearLayout jeu1;
    LinearLayout jeu2;
    Ecouteur ec;
    Partie partie;
    LinearLayout parentOrigine;
    LinearLayout nouveauParent;
    View carte;
    View carteJoue;

    GestionDB instance;
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        score = findViewById(R.id.score);
        chrono = findViewById(R.id.chrono);
        cartesRestantes = findViewById(R.id.cartesRestantes);

        LinearLayout[] pilesTab = {findViewById(R.id.pileDecroissante1), findViewById(R.id.pileDecroissante2),
                findViewById(R.id.pileCroissante1), findViewById(R.id.pileCroissante2)};

        undo = findViewById(R.id.undoButton);

        jeu1 = findViewById(R.id.jeu1);
        jeu2 = findViewById(R.id.jeu2);
        ec = new Ecouteur();

        //créer partie & ouvrir BD
        partie = new Partie();
        instance = GestionDB.getInstance(getApplicationContext());
        instance.ouvrirBD();
        i = new Intent(JeuActivity.this, DBActivity.class);

        //ondrag listener pour les linear layout, on touch pour les textView
        for (int i = 0; i < pilesTab.length; i++) {
            pilesTab[i].setOnDragListener(ec);
            //pilesTab[i].getChildAt(1).setOnTouchListener(ec);
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

        //start chrono
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.chrono.setBase(elapsedRealtime);
        this.chrono.start();
    }


    private class Ecouteur implements View.OnClickListener, View.OnDragListener, View.OnTouchListener {

        @Override
        public boolean onDrag(View source, DragEvent event) { //linear layout
            switch (event.getAction()) {
                case DragEvent.ACTION_DROP:
                    carte = (View) event.getLocalState();
                    parentOrigine = (LinearLayout) carte.getParent();
                    nouveauParent = (LinearLayout) source;
                    String valeurCarte = ((TextView) carte).getText().toString();
                    String tagPile = nouveauParent.getTag().toString();

                    //si pas des moves possibles, renvoye a la prochaine activité


                    //valider si move est valide en fonction de la pile choisie
                    if (partie.moveEstValide(valeurCarte, tagPile)) {
                        //6. enleve carte du jeu1/jeu2
                        parentOrigine.removeView(carte);
                        //7. placer carte dans pile: new parent & add carte

                        // enlever textview de base et remplacer avec carte
                        //nouveauParent.removeAllViews();
                        nouveauParent.addView(carte, 1);
                        carteJoue = carte; // on garde en memoire la carte joué - pour UNDO

                        //enleve carte du jeu[][] et du paquet de cartes
                        if(partie.enleverCarte(valeurCarte, tagPile)){
                            // si replacement des 2 cartes est necessaire, cette fonc retourne un bool pour faire les changements visuels:
                            remplacerCartes();
                        }
                        else{
                            undo.setEnabled(true);
                        }
                        updateCartesRestantes();
                        updateScore(valeurCarte, tagPile);
                        carte.setVisibility(View.VISIBLE);
                    }
                    else{ //move invalide - retourner textView
                        carte.setVisibility(View.VISIBLE);
                    }
                    checkMovesPossibles();
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    carte = (View) event.getLocalState();
                    carte.setVisibility(View.VISIBLE);
                    checkMovesPossibles();
                    break;

            }

            return true;
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
                score.setText(String.valueOf(partie.getScore())); //score updaté
                cartesRestantes.setText(String.valueOf(partie.getCartesRestantes()));
                undo.setEnabled(false);
            }
        }
    }

    public void updateCartesRestantes(){
        cartesRestantes.setText(String.valueOf(partie.getCartesRestantes()));
    }
    public void updateScore(String carte, String tagPile){
        partie.updateScore(carte, tagPile);
        score.setText(String.valueOf(partie.getScore()));


    }
    public void remplacerCartes() {
        //index 0,1 = tag du LinearLayout a remplacer
        //(tag du linearlayout = 00,01,02 ...)
        //vector of integers : row0, column0 , 0 , 1

        int row1 = partie.getTagsRemplacer().elementAt(0);
        int column1 = partie.getTagsRemplacer().elementAt(1);
        int row2 = partie.getTagsRemplacer().elementAt(2);
        int column2 = partie.getTagsRemplacer().elementAt(3);
        LinearLayout temp = jeu1;
        LinearLayout temp2 = jeu1;
        if(row1 == 1){
            temp = jeu2;
        }
        if(row2 == 1){
            temp2 = jeu2;
        }

        createTextView(row1, column1, temp);
        createTextView(row2, column2, temp2);
        partie.getTagsRemplacer().clear();
        undo.setEnabled(false);
    }

    public void createTextView(int row1, int column1, LinearLayout jeu){
        TextView carte = new TextView(this);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
            carte.setLayoutParams(layoutParams);

            carte.setText(String.valueOf(partie.getJeu().getJeuCartes()[row1][column1].getValeur()));
            carte.setBackgroundColor(Color.parseColor("#9990E6"));
            carte.setTextSize(24);
            carte.setPadding(5,5,5,5);
            carte.setTypeface(null, Typeface.BOLD);
            carte.setGravity(Gravity.END);
            LinearLayout temp = ((LinearLayout)jeu.getChildAt(column1));
            temp.addView(carte);
            temp.getChildAt(0).setOnTouchListener(ec);
    }

    public void checkMovesPossibles(){
        //sauver score & redirect a la page de score
        if(!partie.checkMovesPossibles()){
            instance.addScore(partie.getScore());
            startActivity(i);
        }
    }
}