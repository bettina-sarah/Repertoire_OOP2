package com.example.tp2_paint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    LinearLayout couleurs;

    Surface surface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ec = new Ecouteur();
    }


    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {

        }
    }










}