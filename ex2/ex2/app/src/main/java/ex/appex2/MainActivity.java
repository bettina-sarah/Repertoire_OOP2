package ex.appex2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
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
                if(source.getTag().equals("rouge") || source.getTag().equals("vert")){
                    couleurCourante = source.getTag().toString();
                    String couleur = "Couleur de l'item: " + couleurCourante;
                    texteCouleur.setText(couleur);
                }
                else if(source.getTag().equals("apercu")){
                    itemCourant = new ItemMinecraft("Bambou", couleurCourante, largeurCourante);

                }

            }

        }

        //foncs seekbar

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            largeurCourante = progress;
            String progresString = "Largeur: " + String.valueOf(largeurCourante);
            texteLargeur.setText(progresString);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            //non utilisé
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            //non utilisé
        }

        //foncs spinner

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class Surface extends View {

        public Surface(Context context) {
            super(context);
        }
        //2. override onDraw (select override/implement methods et cherche ondraw)
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            itemCourant.dessiner(canvas);

        }
    }


}