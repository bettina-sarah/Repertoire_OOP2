package com.example.annexe13_bieres;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Vector;

public class GestionDB  extends SQLiteOpenHelper {

    private static GestionDB instance;

    private SQLiteDatabase database;

    public GestionDB(Context context) {
        super(context, "db", null, 1);
    }

    public static GestionDB getInstance(Context contexte){

        if(instance == null){
            instance = new GestionDB(contexte);
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //create ton table avec ajouter une evaluation
        db.execSQL("CREATE table evaluation ( _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nom TEXT, microbrasserie TEXT, nbEtoiles INTEGER);");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //drop table & rappelle oncreate

    }

    public void ouvrirBD(){
        database = getWritableDatabase();
    }

    public void fermerBD(){
        database.close();
    }


    public void ajouterEvaluation(Evaluation evAjouter){
        //créer ContentValue
        ContentValues c = new ContentValues();

        c.put("nom", evAjouter.getNom());
        c.put("microbrasserie", evAjouter.getMicrobrasserie());
        c.put("nbEtoiles", evAjouter.getEvaluation());

        database.insert("evaluation", null, c);

    }

    public Vector<String> get3MeilleuresBieres() { //throws Exception
        Vector<String> v = new Vector<>();
        //   table, columns, selection , selectionargs null (where), groupby, having, orderby, limit 3
        Cursor c = database.query("evaluation", new String[]{"nom"}, null, null, null, null, "nbEtoiles DESC", "3");
        //RAWQUERY: select nom from evaluation order by nbEtoiles DESC LIMIT 3, null
        while (c.moveToNext()) {
            v.add(c.getString(0));
        }
        //if v.size() < 3 {

        //throw new Exception ("moins de 3 elem"); -- tu la catch dans le main
    //}
        return v;
    }
}
