package ex.appex2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

        //attacher listeners aux boutons & seekbar


        //configurer le spinner
        //creer array adapter & linker avec spinner

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, choix);
//        spin.setAdapter(adapter);
//        spin.setOnItemSelectedListener(ec);

    }

    private class Ecouteur implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

        @Override
        public void onClick(View v) {

        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}