package com.example.appcafbuckstar;
import Modele.*; //??? marche pas

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.chip.ChipGroup;

import java.text.DecimalFormat;

//BUILD SUPPRIME FOLDER POUR LA REMISE
public class MainActivity extends AppCompatActivity {
    Ecouteur ec;
    LinearLayout parent;
    ChipGroup chipGroup;
    TextView previewText;
    Button ajouter;
    ImageView previewImage;

    Commande commande;

    ListeProduits liste;
    TextView total;
    DecimalFormat df;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = findViewById(R.id.parent);
        liste =  new ListeProduits();
        commande = new Commande();
        chipGroup = findViewById(R.id.grandeurChips);
        previewText = findViewById(R.id.previewText);
        ajouter = findViewById(R.id.boutonAjouter); // pour réactiver
        previewImage = findViewById(R.id.previewImage);
        total = findViewById(R.id.total);
        ec = new Ecouteur();

        chipGroup.setSelectionRequired(true); //permet pas de deselectionner tous les chip
        chipGroup.setSingleSelection(true); //1 doit etre selectionné
        // PETIT selectionné par defaut - A FAIRE

        for (int i = 0; i < parent.getChildCount(); i++){
            LinearLayout miniLayout = (LinearLayout) parent.getChildAt(i); //cast en l.l
            for(int j = 0; j < miniLayout.getChildCount(); j++){
                //attacher tous les listener pour chaque type de widget
                //1. ImageView - les cafés
                if(miniLayout.getChildAt(j) instanceof ImageView){
                    if(miniLayout.getChildAt(j).getTag()!="previewImage"){
                        //pour pas attacher listener sur le imageview qui montre
                        //le sommaire visuel de la commande
                        miniLayout.getChildAt(j).setOnClickListener(ec);
                    }
                }
                //2. chipgroup
                else if (miniLayout.getChildAt(j) instanceof ChipGroup){
                    //.... code ici???
                }
                //3. boutons ajouter, effacer, commander
                else if(miniLayout.getChildAt(j) instanceof Button){
                    miniLayout.getChildAt(j).setOnClickListener(ec);
                }
            }
        }


    }

    private class Ecouteur implements View.OnClickListener{
        @Override
        public void onClick(View source) {

            if(source instanceof ImageView){
                 //quel type de café - tag identique a la clé.
                String completBoisson = source.getTag() + " " + chipGroup.getFocusedChild().getTag();
                //a verifier aussi le chip? comment???

                Produit boissonChoisi = liste.recupererProduit(completBoisson);

                // montrer preview Text
                previewText.setText(boissonChoisi.getNom() + " " +
                        boissonChoisi.getNbCalories() + " " + boissonChoisi.getPrix() + "$");
                //activer bouton Ajouter
                ajouter.setEnabled(true);
                if(source.getTag() == ajouter.getTag()){
                    //ajouter café choisi a la commande
                    commande.ajouterProduit(liste.recupererProduit(completBoisson));
                    total.setText(commande.getTotal()); // a convertir en string?
                    //? decimal format
                    //ajouter dynamiquement un imageview? A FAIRE


                }



            }


        }
    }
}