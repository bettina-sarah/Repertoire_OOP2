package com.example.annexe4b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    for(int i=0; i<parent.getChildCount(); i++){
//        if(parent.getchildAt(i) instanceof LinearLayout){
//            //boucle pour parcourir les boutons .. mais jai 4 linear layout faudrait modifier
                //boucle pour parcourir les boutons et attacher event listenetr
//        }
//    }
}