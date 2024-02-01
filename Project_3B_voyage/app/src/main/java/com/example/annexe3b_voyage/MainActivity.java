package com.example.annexe3b_voyage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;

    ImageView imageAvion, imageHotel;
    TextView textAvion, textHotel, quantiteTotal;
    Button boutonTotal;
    int volsInt = 0;
    int semainesInt = 0;

    BilletAvion volsTotal;
    HebergementHotel hotelTotal;
    Commande commande;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. declarer variables et capturer par ID
        imageAvion = findViewById(R.id.avion);
        textAvion = findViewById(R.id.quantiteVol);
        imageHotel = findViewById(R.id.hotel);
        textHotel = findViewById(R.id.semainesHotel);
        boutonTotal = findViewById(R.id.total);
        quantiteTotal = findViewById(R.id.quantiteTotal);

        //2. créer ecouteur & attacher listener au composantes en haut
        ec = new Ecouteur();
        imageAvion.setOnClickListener(ec);
        imageHotel.setOnClickListener(ec);
        boutonTotal.setOnClickListener(ec);

        textAvion.setText(volsInt + " vols");
        textHotel.setText(semainesInt + " semaines");
        quantiteTotal.setText(0 + " $");

    }

    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View source) {
            if(source==imageAvion){
                volsInt++;
                textAvion.setText(volsInt + " vols");
            }
            else if(source==imageHotel){
                semainesInt++;
                textHotel.setText(semainesInt + " semaines");
            }
            else{ //button total est clicked
                volsTotal = new BilletAvion(volsInt);
                hotelTotal = new HebergementHotel(semainesInt);
                commande = new Commande();
                commande.ajouterProduit(volsTotal);
                commande.ajouterProduit(hotelTotal);
                double megaTotal = commande.grandTotal();

                DecimalFormat df = new DecimalFormat("0.00$");
                quantiteTotal.setText(df.format(megaTotal));
            }

        }
    }
}