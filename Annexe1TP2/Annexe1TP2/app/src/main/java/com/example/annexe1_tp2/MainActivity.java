package com.example.annexe1_tp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Surface surface;
    ConstraintLayout parent;
    Button button;
    EditText pointX, pointY;
    Ecouteur ec;
    Path path;
    float x,y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = findViewById(R.id.parent);
        button = findViewById(R.id.button);
        pointX = findViewById(R.id.PointX);
        pointY = findViewById(R.id.PointY);

        ec = new Ecouteur();
        path = new Path();

        surface = new Surface(this);
        surface.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        surface.setBackgroundColor(Color.CYAN);
        parent.addView(surface);
        button.setOnClickListener(ec);
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
            carre.setColor(Color.BLACK);
            canvas.drawPath(path,carre);
        }
    }

    public class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            x = Float.parseFloat(pointX.getText().toString());
            y = Float.parseFloat(pointY.getText().toString());
            if(path.isEmpty()){
                path.moveTo(x,y);
            }
            else{
               path.lineTo(x,y);
            }

            surface.invalidate();
        }
    }

}