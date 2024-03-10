package ex.appex2;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.AlertDialog;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    Vector <String> itemsChoix;
    TextView texteCouleur;
    Button boutonRouge;
    Button boutonVert;
    SeekBar seekBar;
    TextView texteLargeur;
    Button boutonApercu;
    ConstraintLayout conteneur;

    Ecouteur ec;

    Surface surface;

    String couleurCourante;
    int largeurCourante;

    ItemMinecraft itemCourant;
    String itemChoisi;

    Canvas canvas;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instancier composantes
        spinner = findViewById(R.id.spinner);
        itemsChoix = new Vector<>();
        texteCouleur = findViewById(R.id.texteCouleur);
        boutonRouge = findViewById(R.id.boutonRouge);
        boutonVert = findViewById(R.id.boutonVert);
        seekBar = findViewById(R.id.seekBar);
        texteLargeur = findViewById(R.id.texteLargeur);
        boutonApercu = findViewById(R.id.boutonApercu);
        conteneur = findViewById(R.id.conteneur);

        ec = new Ecouteur();

        //valeurs par defaut au debut:
        couleurCourante = "rouge";
        largeurCourante = 5;

        canvas = new Canvas();


        //attacher listeners aux boutons & seekbar
        boutonRouge.setOnClickListener(ec);
        boutonVert.setOnClickListener(ec);
        boutonApercu.setOnClickListener(ec);

        seekBar.setOnSeekBarChangeListener(ec);

        //remplir vecteur items
        itemsChoix.add("Bambou");
        itemsChoix.add("Cloture");
        itemsChoix.add("Echelle");

        // configurer le spinner - creer array adapter & linker avec spinner

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsChoix);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(ec);

        //configurer surface, ondraw sur conteneur

        surface = new Surface(this);
        surface.setLayoutParams(new LinearLayout.LayoutParams(-1,-1));
        surface.setBackgroundColor(Color.LTGRAY);

        conteneur.addView(surface);


    }

    private class Ecouteur implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, AdapterView.OnItemSelectedListener {

        @Override
        public void onClick(View source) {

            if(source instanceof Button){
                //boutons rouge & vert
                if(source.equals(boutonRouge) || source.equals(boutonVert)){
                    couleurCourante = source.getTag().toString(); //hex

                    if(source.equals(boutonRouge)){
                        String couleur = "Couleur de l'item: rouge";
                        try{
                            if(itemChoisi=="Bambou"){ //si bambou & rouge
                                throw new ItemException();
                            }
                            texteCouleur.setText(couleur);
                        }
                        catch(ItemException ie){
                            creerAlertDialog(ie.getMessage());
                            texteCouleur.setText("Reesayer");
                            //on peut mettre le focus sur le champs a reessayer
                            spinner.requestFocus();
                        }

                    }
                    else if(source.equals(boutonVert)){
                        String couleur = "Couleur de l'item: vert";
                        texteCouleur.setText(couleur);
                        }
                }

                else if(source.equals(boutonApercu)){
                    itemCourant = new ItemMinecraft(itemChoisi, couleurCourante, largeurCourante);
                    //je peux pas appeler desinner ici a cause de manque de canvas qui est dans surface??

                }

            }

        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            //position: 0 , 1, 2 lequel qu'on a select
            TextView textView = (TextView) view;
            //pcq item des spinner c des textview
            itemChoisi = textView.getText().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            largeurCourante = progress;
            String progresString = "Largeur: " + String.valueOf(largeurCourante);
            texteLargeur.setText(progresString);

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }


    }

    public class Surface extends View {
        Paint crayon;

        public Surface(Context context) {
            super(context);
            crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon.setStyle(Paint.Style.STROKE);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if(itemCourant != null){ //ca dessine rien... ca rappelle pas dessiner... mais si je mets pas if != null, app crash....
                //erreur source code doesnt match bytecode....
               itemCourant.dessiner(canvas, crayon);
            }



        }
    }

    public void creerAlertDialog(String message) {


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        //on peut faire ca !!
        builder.setMessage(message)
                .setTitle("Mauvaise couleur!");


        AlertDialog dialog = builder.create();
        dialog.show();
    }


}