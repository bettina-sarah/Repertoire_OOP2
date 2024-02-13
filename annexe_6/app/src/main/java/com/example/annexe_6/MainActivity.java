package com.example.annexe_6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    //3. declarer un objet dla classe Surface
    Surface surface;
    ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = findViewById(R.id.parent);
        //4. instancier l'objet
        surface = new Surface(this); // on est dans main activity
        //5. donner une taille a la surface de dessin
        surface.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)); //en pixel, pas en dp (100, 200)
        // dpToPx(200), dpToPx(300)

        surface.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_700));
        //6. ajouter surface dessin au constraint Layout (parent)

        parent.addView(surface);

    }

    public int dpToPx (int dp){
        //convertir une mesure de dp en px
        float densite = this.getResources().getDisplayMetrics().density;
        return Math.round(dp * densite);

    }

    //. 1 creer classe qui extend view, super
    private class Surface extends View {
        Paint crayon;

        public Surface(Context context) {
            super(context);
            crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon.setStrokeWidth(10); //par defaut width de 1
            crayon.setStyle(Paint.Style.FILL); //par defaut fill
            //countours smooth
        }
        //2. override onDraw (select override/implement methods et cherche ondraw)
        @SuppressLint("DrawAllocation")
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            crayon.setColor(Color.GREEN);
            canvas.drawCircle(100,100,80,crayon);
            crayon.setColor(Color.RED);
            canvas.drawArc(new RectF(),20,70,true,crayon);

        }
    }

}