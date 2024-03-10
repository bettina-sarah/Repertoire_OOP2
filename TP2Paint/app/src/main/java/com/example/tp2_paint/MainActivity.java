package com.example.tp2_paint;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import yuku.ambilwarna.AmbilWarnaDialog; //colorpicker
import android.provider.MediaStore; //pour entregistrer
import android.widget.Toast;

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

    Vector <Forme> formesUndo;

    boolean undoPressed;
    String motFormeCourante;
    Forme formeCourante;

    Forme formeTemporaire;

    boolean triangleFait;
    boolean triangle1erPoint;
    boolean triangle2emePoint;
    boolean triangle3emePoint;

    int largeurCourante;
    Bitmap pixelPipette;
    float x, y;
    AmbilWarnaDialog dialog;


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

        triangle1erPoint = false;
        triangle2emePoint = false;
        triangle3emePoint = false;

        formesDessiner = new Vector<>();
        formesUndo = new Vector<>();
        undoPressed = false;

        backgroundCouleur = "#FF000000";
        //valeurs par defaut pour que l'app crash pas: largeur, couleur, forme
        largeurCourante = 30;
        couleurCourante = "#FFFFFF";
        x = 4;
        y = 5;

        motFormeCourante = "crayon"; //defaut

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
                    motFormeCourante = "pipette";
                    //chercher pixel x y se fait en clickant le canvas - couleur crayon pas setté ici
                }

                else if(source.getTag().equals("palette")){
                    palette();
                }

                else if(source.getTag().equals("undo")){
                    undo();
                    undoPressed = true;
                }

                else if(source.getTag().equals("redo")){
                    redo();
                }


                else if(source.getTag().equals("enregistrer")){
                    enregistrer();
                }

                //sinon: forme a dessiner
                else{
                    motFormeCourante = source.getTag().toString();
                }

            }
        }
    }

    private void palette() {
        dialog = new AmbilWarnaDialog(MainActivity.this, 0xFFFFA500 , new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog dialog, int couleur) {
                couleurCourante = couleurIntToString(couleur);
            }

            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                // cancel was selected by the user
            }
        });

        dialog.show();
    }

    public String couleurIntToString(int couleur){
        String couleurString = String.format("#%08X", couleur);
        return couleurString;
    }

    private void undo(){

        //sauver derniere forme et la stocker dans un autre vecteur
        //seulement si vector de formes dessiné a des elements!
        if(formesDessiner.size()>0){
            formeTemporaire = formesDessiner.lastElement();
            formesUndo.add(formeTemporaire);
            //enlever du vecteur courant
            formesDessiner.removeElement(formeTemporaire);
            formeTemporaire = null;
        }
        surface.invalidate();

    }

    private void redo(){

        if(formesUndo.size()>0) {
            formeTemporaire = formesUndo.lastElement();
            //enlever du vecteur courant
            formesDessiner.add(formeTemporaire);
            formesUndo.removeElement(formeTemporaire);
            formeTemporaire = null;
        }
        surface.invalidate();

    }

    private void enregistrer(){
        Bitmap imageSauve = surface.getBitmapImage();
        boolean isSauve = false;

        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "sss.png");

        try {
            FileOutputStream fichierOS = new FileOutputStream(file);
            imageSauve.compress(Bitmap.CompressFormat.PNG, 100, fichierOS);




            ContentValues values = new ContentValues();
            //rendre l'image accessible via mediastore dans gallery
            this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            fichierOS.flush(); // vider la memoire cache & ferme
            fichierOS.close();
            isSauve = true;
        } catch (IOException e) {
            //Input-out-exception souvent avec FileOutputStream ou ecrire fichier
            //si storage externe est pâs dispo etc
            e.printStackTrace();
        }

        //ERREUR:
        //W/ContextImpl: Failed to ensure /storage/1E0A-3A05/Android/data/com.example.tp2_paint/files/content:/media/external/images/media: android.os.ServiceSpecificException:  (code -22)
        if(isSauve){
            Toast enregistrerConfirme = new Toast(this);
            enregistrerConfirme.setText("Enregistrement sauvé");
            enregistrerConfirme.show();
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




    private class EcouteurTouch implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                x = event.getX();
                y = event.getY();

                formesUndo.clear(); //si nouvelle forme dessiné,
                //ca empeche qu'on peut faire redo apres

                if(triangle2emePoint){ //si jai capturé mes 2 points ...
                    if(formeCourante instanceof Triangle){
                        ((Triangle)formeCourante).troisiemePoint(x,y);
                        triangle2emePoint = false;
                        triangle3emePoint = true;
                        triangle1erPoint = false;
                        //triangleFait = true;
                        surface.invalidate();
                    }
                }


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
                        break;

                    case "triangle":
                            formeCourante = new Triangle(largeurCourante, couleurCourante, x, y);
                            triangle1erPoint = true;

                        break;
                    case "pipette":
                        pixelPipette = surface.getBitmapImage();
                        //chercher couleur ici via pixel avec les x & y:
                        //transtyper les float de l'event:
                        int intX = (int)x;
                        int intY = (int)y;
                        int couleurPipette = pixelPipette.getPixel(intX, intY);
                        //transtyper int en String et faire outil crayon
                        couleurCourante = couleurIntToString(couleurPipette);
                        //revenir au crayon
                        motFormeCourante = "crayon";
                        break;


                }
                surface.invalidate();
            }

            else if (event.getAction() == MotionEvent.ACTION_MOVE){
                x = event.getX();
                y = event.getY();

                formeCourante.move(x,y);

                surface.invalidate();
            }

            else if (event.getAction() == MotionEvent.ACTION_UP){
                x = event.getX();
                y = event.getY();

                if(formeCourante instanceof Triangle){

                    ((Triangle)formeCourante).deuxiemePoint(x,y);
                    formesDessiner.add(formeCourante);
                    triangle2emePoint = true; //maintenant on peut prendre 3eme point si on repese sur canvas
                    surface.invalidate();
                    triangle1erPoint = false;
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


            for (Forme forme : formesDessiner) {
                forme.dessiner(canvas);
            }
        }
        public Bitmap getBitmapImage() {

            this.buildDrawingCache();
            Bitmap bitmapImage;
            bitmapImage = Bitmap.createBitmap(this.getDrawingCache());
            this.destroyDrawingCache();

            return bitmapImage;
        }
    }



}