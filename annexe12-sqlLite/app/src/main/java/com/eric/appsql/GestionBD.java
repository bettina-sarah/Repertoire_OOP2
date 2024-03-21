package com.eric.appsql;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;


public class GestionBD extends SQLiteOpenHelper {

    //instance unique de la classe Singleton GestionBD
    private static GestionBD instance; //static pour qu'on  accede avec get instance
    //objet GestionDB appelé instance - singleton

    private SQLiteDatabase database;

    //tous les action sur le db - juste une maniere de les faire sans création objet.
    //singleton remplace une reference STATIQUE

    // méthode de base pour un Singleton
    // crée une instance une seule fois quand getInstance est appelé qui appele le singleton
    public static GestionBD getInstance(Context contexte) {
        if (instance == null)
            instance = new GestionBD(contexte);
        return instance;
    }


    //! constructor est PRIVATE pcq singleton
    //on crée une seule fois comme ca
    private GestionBD(Context context) {
        //!! IMPORTANT, lorsqu’on instancie le singleton, on associe le paramètre Context à l’appel de getApplicationContext()
        // qui retourne le contexte de l’app comme le singleton qui est accessible de tout l’app.
        //pas this.



        super(context, "db", null, 1); //db nom de la db
        //factory: fabrique de curseurs pour avoir des curseurs specifiques
        //version:alter table.. etc.. version 2,3 .. (on upgrade)

    }


    //oncreate UNE FOIS quand l'app est installé!!!! donc acces au db une seule fois.
    //si erreur et oncreate est refaite; delete app de l'emulateur et re-execute programme
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE table Inventeur ( _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nom TEXT, origine TEXT, invention TEXT, annee INTEGER);");

        ajouterInventeur(new Inventeur ( "Laszlo Biro", "Hongrie", "Stylo à bille", 1938), db);
        ajouterInventeur ( new Inventeur ("Benjamin Franklin", "États-Unis", "Paratonnerre", 1752), db);
        ajouterInventeur ( new Inventeur ( "Mary Anderson", "États-Unis", "Essuie-Glace", 1903), db);
        ajouterInventeur ( new Inventeur ( "Grace Hopper", "États-Unis", "Compilateur", 1952), db);
        ajouterInventeur ( new Inventeur ( "Benoit Rouquayrot", "France", "Scaphandre", 1864), db);

    }

    public void ajouterInventeur(Inventeur inventeur, SQLiteDatabase db){ //fait le insert


        //créer contentvalue comme un hashtable

        ContentValues cv = new ContentValues();
        cv.put("nom", inventeur.getNom());
        cv.put("origine", inventeur.getOrigine());
        cv.put("invention", inventeur.getInvention());
        cv.put("annee", inventeur.getAnnee());

        db.insert("Inventeur", null, cv);
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP table IF EXISTS Inventeur;");
        onCreate(db);

    }

    public void ouvrirBD(){
        database = this.getWritableDatabase(); //acces a la bd apres le oncreate a été faite / apres que l'app est installé
        //!! toujours fermer l'acces
    }
    public void fermerAcces(){
        database.close();
    }

    //requête permettant de retourner le nom de toutes les inventions de la table
    public Vector<String> retournerNomsInventions(){

    Vector<String> inventions = new Vector<>();

    //selectionArgs: where
        // RAWQUERY sans ;
        //cursor: ensemble de resultats - toujours AVANT la 1ere ligne de resultat
    Cursor cursor = database.rawQuery("select invention FROM inventeur", null);


        while(cursor.moveToNext()){
        inventions.add(cursor.getString(0)); //seulement une colonne
        }
    cursor.close();

    return inventions;
    }

    //retourner si le nom d’un inventeur et l’invention fournis en paramètre sont une bonne association ou non
    public boolean aBonneReponse(String nom, String invention){

        String []tab = {nom, invention};
        Cursor cursor = database.rawQuery("select invention FROM inventeur WHERE nom = ? AND invention = ?", tab);
        //retourne 1 seul resultat. return true si ca match avec reponse
        //moveToNext ok aussi
        boolean resultat = cursor.moveToFirst();
        cursor.close();
            return resultat;
    }


}
