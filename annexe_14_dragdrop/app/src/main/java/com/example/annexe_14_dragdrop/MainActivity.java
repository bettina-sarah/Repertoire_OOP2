package com.example.annexe_14_dragdrop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout parent;
    Ecouteur ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //drag & drop avec OnDragListener & OnTouchListener

        parent = findViewById(R.id.parent);
        ec = new Ecouteur();

        for(int i=0; i<parent.getChildCount(); i++){
            //LinearLayout: OnDragListener
            //transtyper pour avoir acces au ptits enfants
            LinearLayout temp = ((LinearLayout)parent.getChildAt(i));
            temp.setOnDragListener(ec);
            //Imageview: View.OnTouchListener
            temp.getChildAt(0).setOnTouchListener(ec);
            }
        }




    private class Ecouteur implements View.OnDragListener, View.OnTouchListener {

        Drawable normal = ContextCompat.getDrawable(MainActivity.this, R.drawable.background_triangle);
        Drawable select = ContextCompat.getDrawable(MainActivity.this, R.drawable.background_triangle_selectionne);

        View jeton = null;

        @Override
        public boolean onDrag(View source, DragEvent event) { //ondrag pour les linearlayout
            //DragEvent action types: ACTION_DRAG_STARTED, ENTERED, LOCATION, EXITED, ENDED, ACTION_DROP
//            ENTERED, EXITED,
            switch(event.getAction()){ // if event.getAction() == DragEvent.ACTION_DRAG_STARTED

                case DragEvent.ACTION_DRAG_ENTERED: //change background si linearlayout selectionné
                    source.setBackground(select);
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    source.setBackground(normal);
                    break;

                case DragEvent.ACTION_DROP:
                    //prendre le jeton invisible de 1ere colonne;
                    //source/objet de startDragAndDrop de touchlistener (param de ca on recupere ici)
                    jeton = (View)event.getLocalState(); //retourne object view donc le jeton
                    //get colonne d'origine
                    LinearLayout parentOrigine = (LinearLayout)jeton.getParent();
                    //enleve le jeton de la 1ere colonne...
                    parentOrigine.removeView(jeton);
                    //source = nouvelle colonne
                    LinearLayout nouveauParent = (LinearLayout) source;
                    nouveauParent.addView(jeton);
                    jeton.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    jeton = (View)event.getLocalState();
                    jeton.setVisibility(View.VISIBLE);
                    source.setBackground(normal);
                    break;




            }





            return true;
        }

        @Override
        public boolean onTouch(View source, MotionEvent event) {
            //jeton grisé ,, donne lapparence qu'on drag
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(source);

            if(Build.VERSION.SDK_INT > 24)
                //param: Clipdata: transmettre qsq on drag si besoin...
                //source: object to be dragged & dropped (LOCALSTATE)
                source.startDragAndDrop(null, shadowBuilder, source,0);
                //the system sends a drag event with action type ACTION_DRAG_STARTED to the drag event listener of all View objects in the current layout.
            else
                source.startDrag(null,shadowBuilder, source, 0);

            source.setVisibility(View.INVISIBLE); //DONNER L'illusion qu'on transporte le jeton (il reste dans le linearlayout de depart
            //et cest le builder qui suit le doigt avec une shadow builder



                return true;
            }
        }
    }

