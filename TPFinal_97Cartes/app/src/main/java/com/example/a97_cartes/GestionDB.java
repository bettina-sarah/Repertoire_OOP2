package com.example.a97_cartes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Vector;

public class GestionDB extends SQLiteOpenHelper {

    private static GestionDB instance;

    private SQLiteDatabase database;

    public GestionDB(Context contexte) {
        super(contexte, "db", null, 1);
    }

    public static GestionDB getInstance(Context contexte) {
        if(instance==null){
            instance = new GestionDB(contexte);
        }
        return instance;
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE table score ( _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "score_valeur INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void ouvrirBD(){
        database = getWritableDatabase();
    }

    public void fermerBD(){
        database.close();
    }

    public void addScore(int scoreAjouter){
        ContentValues cv = new ContentValues();
        cv.put("score_valeur", scoreAjouter);
        database.insert("score", null, cv);

    }

    public Vector<String> getMeilleursScores(){

        Vector<String> v = new Vector<>();
        //   table, columns, selection , selectionargs null (where), groupby,
        //   having, orderby, limit 3

        Cursor c = database.query("score", new String[]{"score_valeur"}, null,
                null, null, null, "score_valeur DESC", null);

        while(c.moveToNext()){
            v.add(c.getString(0));
        }
        return v;
    }
}
