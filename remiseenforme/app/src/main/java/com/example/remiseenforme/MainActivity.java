package com.example.remiseenforme;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Time;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    LinearLayout parentImageView;
    EditText heureDebut;
    EditText minutesDebut;
    EditText heureFin;
    EditText minutesFin;
    ProgressBar progres;
    Button boutonEnregistrer;
    //init objets:
    String exerciceChoisi; //a partir du imageview
    int minutesCalcule;
    Exercice exRentre;

    PlanEntrainement plan;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ec = new Ecouteur();

        //capturer tous les composantes
        parentImageView = findViewById(R.id.parentimageView);
        heureDebut = findViewById(R.id.heureDebut);
        minutesDebut = findViewById(R.id.minutesDebut);
        heureFin = findViewById(R.id.heureFin);
        minutesFin = findViewById(R.id.minutesFin);
        boutonEnregistrer = findViewById(R.id.boutonEnregistrer);
        progres = findViewById(R.id.progressBar);
        progres.setMax(120);
        plan = new PlanEntrainement(120);

        //attacher ecouteurs aux imageview & bouton enregistrer

        for(int i=0; i<parentImageView.getChildCount(); i++){
            if(parentImageView.getChildAt(i) instanceof ImageView){
                parentImageView.getChildAt(i).setOnClickListener(ec);
            }
        }
        boutonEnregistrer.setOnClickListener(ec);


    }

    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View source) {
            //capter type d'exercice (quel imageview a été clické:
            if(source instanceof ImageView){
                exerciceChoisi = source.getTag().toString();
            }

            if(source instanceof Button){ //si bouton enregistrer:

                //capter tous les valeurs rentrés - transf en string - parse en int
                String strHDebut = heureDebut.getText().toString();
                int intHDebut = Integer.parseInt(strHDebut);

                String strMDebut = minutesDebut.getText().toString();
                int intMDebut = Integer.parseInt(strMDebut);

                String strHFin = heureFin.getText().toString();
                int intHFin = Integer.parseInt(strHFin);

                String strMFin = minutesFin.getText().toString();
                int intMFin = Integer.parseInt(strMFin);

                //calculer total en minutes:

                minutesCalcule = calculerTemps(intHDebut, intMDebut, intHFin, intMFin);
                if(minutesCalcule==-1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Erreur - heure impossible");
                    builder.show();

                }
                else{
                    //creer obj Exercice & ajouter au plan

                    exRentre = new Exercice(exerciceChoisi, minutesCalcule);
                    plan.ajouterExercice(exRentre);

                    //aug progrBar

                    progres.setProgress(plan.calculerTotal());
                    int duration = Toast.LENGTH_SHORT;

                    CharSequence text = "exercice ajouté " + exerciceChoisi + ":" + exRentre.getDureeMinutes() + "minutes";

                    Toast toast = Toast.makeText(MainActivity.this, text, duration);
                    toast.show();

                }


            }
            if(plan.calculerTotal() >=120){

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("OBJECTIF ATTEINT!\n");
                builder.show();
            }

        }
    }

    public int calculerTemps (int heuredebut, int minutedebut, int heurefin, int minutefin){
        if(heurefin<heuredebut){
            return -1;
        }
        if (heuredebut==heurefin){
            return minutefin-minutedebut;
        }
        else{ //si plus q'une heure
            if(minutedebut>minutefin){
                return 60*(heurefin-heuredebut)-Math.abs(minutefin-minutedebut);
            }
            return 60*(heurefin-heuredebut)+(minutefin-minutedebut);
        }
    }
}