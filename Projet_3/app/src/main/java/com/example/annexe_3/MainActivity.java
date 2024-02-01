package com.example.annexe_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    ImageView imageBidon;
    ImageView imageBouteille;
    ImageView imageVerre;
    TextView quantiteEau;
    ProgressBar barreProgres;
    int quantiteInt = 0;
    int quantiteBidon = 1500;
    int quantiteBouteille = 330;
    int quantiteVerre = 150;

    //jaurais pu avoir une seule quantité que je add 1500, 330 ou 150 selon le bouton jenre quantite +=1500


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageBidon = findViewById(R.id.bidon);
        imageBouteille = findViewById(R.id.bouteille);
        imageVerre = findViewById(R.id.verre);

        quantiteEau = findViewById(R.id.quantiteEau);

        barreProgres = findViewById(R.id.progressBar);
        barreProgres.setMax(2000); // comme ca elle progress incremente a 2000

        quantiteEau.setText(quantiteInt + "  mL");

        ec = new Ecouteur();

        //inscript click listener au imageview
        imageBidon.setOnClickListener(ec);
        imageBouteille.setOnClickListener(ec);
        imageVerre.setOnClickListener(ec);


    }


    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View source) {

            //if, else if, else
            if(source==imageBidon){
                quantiteInt +=quantiteBidon;
                barreProgres.setProgress(quantiteInt);
                quantiteEau.setText(quantiteInt + "  mL" );


            }

            else if(source==imageBouteille){
                quantiteInt +=quantiteBouteille;
                barreProgres.setProgress(quantiteInt);
                quantiteEau.setText(quantiteInt + "  mL" );

            }

            else{
                quantiteInt +=quantiteVerre;
                barreProgres.setProgress(quantiteInt);
                quantiteEau.setText(quantiteInt + "  mL" );
            }

            if(quantiteInt>=2000){
                CharSequence text = "FÉLICITATIONS! 2 LITRES ATTEINT";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(MainActivity.this, text, duration);
                toast.show();

                //snackbar aussi possible
                Snackbar mySnackBar = Snackbar.make(source, text, BaseTransientBottomBar.LENGTH_LONG);
                mySnackBar.show();
            }

        }
    }
}