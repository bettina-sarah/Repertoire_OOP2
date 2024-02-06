package com.example.annexe4b;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;

    LinearLayout clavierNumerique;
    EditText motDePasse;

    String tryCode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ec = new Ecouteur();
        motDePasse = findViewById(R.id.motDePasse);

        clavierNumerique = findViewById(R.id.boutonNumerique);


        for(int i=0; i<clavierNumerique.getChildCount(); i++){
            if(clavierNumerique.getChildAt(i) instanceof LinearLayout){

                LinearLayout miniLayout = (LinearLayout)(clavierNumerique.getChildAt(i));
                for (int j=0; j<miniLayout.getChildCount(); j++){
                    ;
                    if(miniLayout.getChildAt(j) instanceof Button){
                        miniLayout.getChildAt(j).setOnClickListener(ec);
                    }
                }
            }
        }
    }


    private class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View source) {

            if(source instanceof Button){

                tryCode += ((Button)source).getText().toString();

                motDePasse.setText(tryCode);

                if(tryCode.equals("1234")){ //pas de ==, get text to strng = object (new string)
                    //1234 est un string literal. mieux avec equals

                    //ou sinon ... get text.tostring.intern() pour l'avoir literal
                    clavierNumerique.setBackgroundColor(Color.parseColor("#009688"));
                }
                else{
                    clavierNumerique.setBackgroundColor(Color.parseColor("#E91E63"));
                }

		tryCode=""; //remettre a zero


            }



        }
    }


}