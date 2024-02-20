package com.example.annexe1_tip2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;



public class MainActivity extends AppCompatActivity {
    Surface surface;
    LinearLayout parent;
    Button button;
    EditText pointX, pointY;
    Ecouteur ec;
    Path path;
    int x,y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pointX = findViewById(R.id.pointX);
        pointY = findViewById(R.id.pointY);
        button = findViewById(R.id.button);
        parent = findViewById(R.id.surfaceDessin);

        ec = new Ecouteur();
        path = new Path();

        surface = new Surface(this);

        surface.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        surface.setBackgroundColor(ContextCompat.getColor(this,R.color.teal_200));
        parent.addView(surface);

        button.setOnClickListener(ec);
    }

    public class Surface extends View {

        Paint crayon;
        public Surface(Context context) {
            super(context);
            crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon.setStyle(Paint.Style.STROKE);
            crayon.setStrokeWidth(10);
        }
        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            crayon.setColor(Color.BLACK);
            canvas.drawPath(path,crayon);
        }
    }



    public class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View source) {

            x = Integer.parseInt(pointX.getText().toString());
            y = Integer.parseInt(pointY.getText().toString());

            if(path.isEmpty()){ //1ere fois
                path.moveTo(x,y);
                }
            else{
                path.lineTo(x,y);
                }

            surface.invalidate();

        }
    }
}