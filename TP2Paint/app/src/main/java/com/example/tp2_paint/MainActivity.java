package com.example.tp2_paint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tp2_paint.forme.Forme;
import com.example.tp2_paint.forme.TraceLibre;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    //declarer tous les objets
    EcouteurClick ecClick;
    EcouteurTouch ecTouch;
    LinearLayout couleurs;
    LinearLayout surfaceDessin;
    LinearLayout choix;
    Surface surface;
    String couleurCourante; // va contenir le tag lié a la couleur

    Vector <Forme> formesDessiner;
    Forme formeCourante;
    Paint crayon;

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
            if(source instanceof Button){
                couleurCourante = source.getTag().toString();
                crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
                crayon.setStrokeWidth(10); //par defaut width de 1
                //A CHANGER largeur de trait
                crayon.setStyle(Paint.Style.FILL);
                crayon.setColor(Color.parseColor(couleurCourante));

            }

            else if(source instanceof ImageView){

                if(source.getTag().equals("remplir")){
                    surface.setBackgroundColor(Color.parseColor(couleurCourante));
                }
                if(source.getTag().equals("crayon")){
                    formeCourante = new TraceLibre(50,couleurCourante);

                   // listeFormes.recupererForme(source.getTag().toString());
                }

               // formeCourante = listeFormes;
                //quel bouton et attacher a un objet Util
               // listeFormes.recupererForme()

                //Forme formeCourante;

              //  listeFormes.;

            }


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

            //on draw vecteur

        }
    }










}