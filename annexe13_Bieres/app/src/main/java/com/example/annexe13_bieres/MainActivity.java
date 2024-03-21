package com.example.annexe13_bieres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button activite2;
    Button activite3;
    Ecouteur ec;

    Intent i2;
    Intent i3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activite2 = findViewById(R.id.boutonActivite2);
        activite3 = findViewById(R.id.boutonActivite3);

        ec = new Ecouteur();
        activite2.setOnClickListener(ec);
        activite3.setOnClickListener(ec);

        i2 = new Intent(MainActivity.this, MainActivity2.class);
        i3 = new Intent(MainActivity.this, MainActivity3.class);

    }

    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View source) {
            if(source == activite2){
                startActivity(i2);
                finish();
            }
            else if(source == activite3){
                startActivity(i3);
                finish();
            }

        }
    }


}