package com.example.tp2_paint.utils;


import java.util.Hashtable;


public class ListeUtils {

        private Hashtable<String, Util> listeUtils;

        public ListeUtils () {
            listeUtils = new Hashtable();
            listeUtils.put("largeurTrait", new LargeurTrait()); //objets dans a completer
            listeUtils.put("pipette", new Pipette());
            listeUtils.put("remplir", new PotPeinture());
            listeUtils.put("palette", new PaletteCouleur());
            listeUtils.put("undo", new Undo());
            listeUtils.put("redo", new Redo());
            listeUtils.put("enregistrer", new Sauvegarde());
        }

        public Util recupererUtil (String cle) {
            return this.listeUtils.get(cle);
        }

    }