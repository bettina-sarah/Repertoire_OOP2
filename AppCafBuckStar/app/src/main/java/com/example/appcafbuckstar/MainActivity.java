package com.example.appcafbuckstar;
import Modele.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
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

        //attacher ecouteurs pour les imageview & boutons
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
                //3. boutons ajouter, effacer, commander
                else if(miniLayout.getChildAt(j) instanceof Button){
                    miniLayout.getChildAt(j).setOnClickListener(ec);
                }
            }
        }
        //attacher ecouteurs pour chipgroup

        chipGroup.setSelectionRequired(true); //permet pas de deselectionner tous les chip
        chipGroup.setSingleSelection(true); //1 doit etre selectionné

        for(int i=0; i<chipGroup.getChildCount(); i++){
            chipGroup.getChildAt(i).setOnClickListener(ec);
            if(i==0){
                Chip petitChip = (Chip)chipGroup.getChildAt(i);
                petitChip.setChecked(true);
            }
        }
    }


    private class Ecouteur implements View.OnClickListener{
        String typeBoisson = "";
        String formatChip = "";
        String completBoisson = "";
        Produit boissonChoisi;
        String bonFormat;
        @Override
        public void onClick(View source) {

            //1. imageview record quel type de boisson
            if(source instanceof ImageView){
                typeBoisson = source.getTag().toString();
                }
            //2. chekcer le chip maintenant & activer bouton
                if(source.getTag().toString().equals("boutonAjouter")){
                    commande.ajouterProduit(boissonChoisi);
                    df = new DecimalFormat("0.00$");
                    bonFormat = df.format(commande.getTotal());
                    total.setText(bonFormat);
                }

                else if (source.getTag().toString().equals("boutonEffacer")){
                    commande.vider();
                    String totaaal = "0.00$";
                    total.setText(totaaal);
                }

                else if (source.getTag().toString().equals("boutonCommander")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Commande envoyée! ");
                    //chercher total encore
                    builder.setMessage("paiement de " + bonFormat + " en cours");
                    builder.show();
                }

                else if(source instanceof AppCompatCheckBox){ //si chip
                    formatChip = source.getTag().toString();
                    ajouter.setEnabled(true);

                    //3. creer string complet == clé:
                    completBoisson =typeBoisson + " " + formatChip;
                    //4. chercher produit
                    boissonChoisi = liste.recupererProduit(completBoisson);
                    System.out.println(completBoisson);
                    //5. push text du produit dans preview
                    String preview = boissonChoisi.getNom() + " " +
                            boissonChoisi.getNbCalories() + " " + boissonChoisi.getPrix() + "$";
                    previewText.setText(preview);
                    System.out.println(preview);
                }
            }

            //? decimal format
            //ajouter dynamiquement un imageview? A FAIRE



        }
    }