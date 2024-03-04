package com.example.tp2_paint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class LargeurTrait extends Dialog {
    MainActivity m;
    SeekBar seekBar;
    Button boutonTrait;
    Ecouteur ec;

    TextView textLargeur;

    int progresFinal;


    public LargeurTrait(@NonNull Context context) {
        super(context);
        m = (MainActivity) context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_largeur_trait);

        boutonTrait = findViewById(R.id.boutonTrait);
        seekBar = findViewById(R.id.seekBar);
        textLargeur = findViewById(R.id.textLargeur);

        ec = new Ecouteur();

        // attacher ecouteurs

        boutonTrait.setOnClickListener(ec);
        seekBar.setOnSeekBarChangeListener(ec);


    }

    private class Ecouteur implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

        @Override
        public void onClick(View v) {
            m.changerLargeurTrait(progresFinal);
            dismiss();

        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            String progresString = "Largeur trait: " + String.valueOf(progress);
            textLargeur.setText(progresString);
            progresFinal = progress;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            //Notification that the user has started a touch gesture.
            // Clients may want to use this to disable advancing the seekbar.


        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}