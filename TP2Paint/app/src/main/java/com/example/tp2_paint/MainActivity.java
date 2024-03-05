package com.example.tp2_paint;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tp2_paint.forme.Cercle;
import com.example.tp2_paint.forme.Efface;
import com.example.tp2_paint.forme.Forme;
import com.example.tp2_paint.forme.Rectangle;
import com.example.tp2_paint.forme.TraceLibre;
import com.example.tp2_paint.forme.Triangle;

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
    String backgroundCouleur;
    Vector <Forme> formesDessiner;
    String motFormeCourante;
    Forme formeCourante;

    int largeurCourante;

    float x, y;

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

        formesDessiner = new Vector<>();
        backgroundCouleur = "#FF000000";

        largeurCourante = 30; // default largeur


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
        surface.setBackgroundColor(Color.parseColor(backgroundCouleur));

        surfaceDessin.addView(surface);
        surface.setOnTouchListener(ecTouch);
    }


    private class EcouteurClick implements View.OnClickListener{

        @Override
        public void onClick(View source) {
            if(source instanceof Button){
                couleurCourante = source.getTag().toString();

            }

            else if(source instanceof ImageView){

                if(source.getTag().equals("remplir")){
                    remplir();
                }

                else if(source.getTag().equals("largeurTrait")){
                    LargeurTrait fenetre = new LargeurTrait(MainActivity.this);
                    fenetre.show();
                }

                else if(source.getTag().equals("pipette")){


                }

                //sinon: forme a dessiner
                else{
                    motFormeCourante = source.getTag().toString();
                }

            }
        }
    }


    private void remplir() {
        surface.setBackgroundColor(Color.parseColor(couleurCourante));
        backgroundCouleur = couleurCourante;

        //ici on modifie tous les formes pour matcher la couleur du fond
        for (Forme forme : formesDessiner) {
                forme.getCrayon().setColor(Color.parseColor(backgroundCouleur));
            }
        }

    public void changerLargeurTrait(int largeur) {
       largeurCourante = largeur;
    }

    public Bitmap getBitmapImage() {

        this.buildDrawingCache();
        bitmapImage = Bitmap.createBitmap(this.getDrawingCache());
        this.destroyDrawingCache();

        return bitmapImage;
    }


    private class EcouteurTouch implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                x = event.getX();
                y = event.getY();


                switch(motFormeCourante){
                    case "crayon":
                        formeCourante = new TraceLibre(largeurCourante,couleurCourante, x, y);
                        formesDessiner.add(formeCourante);
                        break;

                    case "effacer":
                        formeCourante = new Efface(largeurCourante, backgroundCouleur, x, y);
                        formesDessiner.add(formeCourante);
                        break;

                    case "cercle":
                        formeCourante = new Cercle(largeurCourante, couleurCourante, x, y);
                        formesDessiner.add(formeCourante);
                        break;

                    case "rectangle":
                        formeCourante = new Rectangle(largeurCourante, couleurCourante, x, y);
                        formesDessiner.add(formeCourante);

                    case "triangle":
                        formeCourante = new Triangle(largeurCourante, couleurCourante, x, y);
                        formesDessiner.add(formeCourante);





                }
                surface.invalidate();
            }

            if (event.getAction() == MotionEvent.ACTION_MOVE){
                x = event.getX();
                y = event.getY();

                formeCourante.move(x,y);
                surface.invalidate();
            }

            if (event.getAction() == MotionEvent.ACTION_UP){
                x = event.getX();
                y = event.getY();

                if(formeCourante instanceof Triangle){
                    ((Triangle)formeCourante).troisiemePoint(x,y);
                    //surface.invalidate();
                }

            }


            return true;
        }
    }

    public class Surface extends View {

        public Surface(Context context) {
            super(context);
        }


        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            //parcourir le vecteur des formes:

            for (Forme forme : formesDessiner) {
                forme.dessiner(canvas);
            }


        }
    }



}