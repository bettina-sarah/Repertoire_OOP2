package com.example.tp2_paint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    //declarer tous les objets
    EcouteurClick ecClick;
    EcouteurTouch ecTouch;

    LinearLayout couleurs;
    LinearLayout surfaceDessin;
    LinearLayout choix;
    Surface surface;

    String couleurCourante; // va contenir le tag lié a la couleur

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialiser les objets

        ecClick = new EcouteurClick();
        ecTouch = new EcouteurTouch();

        couleurs = findViewById(R.id.couleurs);
        surfaceDessin = findViewById(R.id.surfaceDessin);
        choix = findViewById(R.id.choix);

        //attacher click listener a tous les boutons et imageviews

        //boutons couleur

        for(int i=0; i<couleurs.getChildCount(); i++){
            if(couleurs.getChildAt(i) instanceof Button){ //pas necessaire mais fait pour securité
                couleurs.getChildAt(i).setOnClickListener(ecClick);
            }
        }

        //imageViews choix

        for(int i=0; i<choix.getChildCount(); i++){
            if(choix.getChildAt(i) instanceof ImageView){ //pas necessaire mais fait pour securité
                choix.getChildAt(i).setOnClickListener(ecClick);
            }
        }

        //créer surface, donner params, attache au parent, add TouchListener

        surface = new Surface(this);
        surface.setLayoutParams(new LinearLayout.LayoutParams(-1,-1));
        surfaceDessin.addView(surface);
        surface.setOnTouchListener(ecTouch);


    }


    private class EcouteurClick implements View.OnClickListener{

        @Override
        public void onClick(View source) {

//            couleurCourante = source.getTag().toString();
//            surface.setBackgroundColor(Color.parseColor(couleurCourante));

        }
    }

    private class EcouteurTouch implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
    }

    public class Surface extends View {

        public Surface(Context context) {
            super(context);
        }



        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);

        }
    }










}