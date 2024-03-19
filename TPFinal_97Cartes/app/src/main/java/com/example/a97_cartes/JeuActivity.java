package com.example.a97_cartes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class JeuActivity extends AppCompatActivity {

    LinearLayout jeu1;
    LinearLayout jeu2;
    Ecouteur ec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);
        LinearLayout [] pilesTab = {findViewById(R.id.pileCroissante1), findViewById(R.id.pileCroissante2),
            findViewById(R.id.pileDecroissante1), findViewById(R.id.pileDecroissante2)};


        jeu1 = findViewById(R.id.jeu1);
        jeu2 = findViewById(R.id.jeu2);
        ec = new Ecouteur();

        //ondrag listener pour les linear layout, on touch pour les textView
        for (int i=0; i<pilesTab.length; i++){
            pilesTab[i].setOnDragListener(ec);
            pilesTab[i].getChildAt(0).setOnTouchListener(ec);
            }

        for (int i=0; i<jeu1.getChildCount(); i++){
            LinearLayout temp = ((LinearLayout)jeu1.getChildAt(i));
            temp.setOnDragListener(ec);
            temp.getChildAt(0).setOnTouchListener(ec);
        }
        for (int i=0; i<jeu2.getChildCount(); i++){
            LinearLayout temp = ((LinearLayout)jeu2.getChildAt(i));
            temp.setOnDragListener(ec);
            temp.getChildAt(0).setOnTouchListener(ec);
        }


        }

    }

    private class Ecouteur implements View.OnDragListener, View.OnTouchListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {


            return true;
        }

        @Override
        public boolean onTouch(View source, MotionEvent event) {
            //shadow builder

            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(source);
            //param: Clipdata: transmettre qsq on drag si besoin...
            //source: object to be dragged & dropped (LOCALSTATE)
            source.startDragAndDrop(null, shadowBuilder, source,0);
            //the system sends a drag event with action type ACTION_DRAG_STARTED to the drag event listener of all View objects in the current layout.
            source.setVisibility(View.INVISIBLE); //DONNER L'illusion qu'on transporte le jeton (il reste dans le linearlayout de depart
            //et cest le builder qui suit le doigt avec une shadow builder
            return true;
        }
    }
}