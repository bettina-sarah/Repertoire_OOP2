package com.example.annexe7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Surface surface;
    ConstraintLayout parent;
    Ecouteur ec;
    float x, y, x2,y2,x3,y3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = findViewById(R.id.parent);
        ec = new Ecouteur();

        surface = new Surface(this);
        surface.setLayoutParams(new LinearLayout.LayoutParams(-1,-1));
        surface.setBackgroundResource(R.drawable.carte);
        parent.addView(surface);
        surface.setOnTouchListener(ec);
    }

    public class Surface extends View {
        Paint carre;
        public Surface(Context context){
            super(context);
            carre = new Paint();
            carre.setStrokeWidth(15);
        }
        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            carre.setColor(Color.RED);
            canvas.drawRect(x-20,y-20,x+20,y+20,carre);
            canvas.drawLine(x,y,x2,y2,carre);
            canvas.drawRect(x3-20,y3-20,x3+20,y3+20,carre);
        }

    }

    public class Ecouteur implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if(event.getAction() == 0){
                x = event.getX();
                y = event.getY();
                x3 = -20;
                y3 = -20;
                surface.invalidate();    
            } else if (event.getAction() == 2) {
                x2 = event.getX();
                y2 = event.getY();
                surface.invalidate();
            } else if (event.getAction() == 1) {
                x3 = event.getX();
                y3 = event.getY();
                surface.invalidate();
            }
            return true;

        }
    }

}