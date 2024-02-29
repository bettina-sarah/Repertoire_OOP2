package com.example.annexe_7;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionHelperInterface;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


//    commence par faire surface de dessin comme la dernière fois
//    setBackgroundResource pour l image
//    et TouchListener

    //2. instaancier les objets (surf dessin & layout parent)
    Surface surface;
    ConstraintLayout parent;

    Ecouteur ec;

    Point depart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = findViewById(R.id.parent);

        //3. create surface
        surface = new Surface(this);

        //4. donner la taille du parent a la surf du dessin
        surface.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        //5. set background de la surf dessin (image):
        surface.setBackgroundResource(R.drawable.carte);
        //6. addview
        parent.addView(surface);

        //attach ontouch listener to surface - make listener
        Ecouteur ec = new Ecouteur();

        surface.setOnTouchListener(ec);

    }

    //1. create surface class
    private class Surface extends View{

        Paint crayon;

        public Surface(Context context) {
            super(context);
        }
    }

    public class Ecouteur implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            depart = new Point(event.getX())


        }
    }
}
