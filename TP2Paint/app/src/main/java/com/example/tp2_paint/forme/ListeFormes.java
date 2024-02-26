package com.example.tp2_paint.forme;

import java.util.Hashtable;

public class ListeFormes {

    private Hashtable<String, Forme> listeFormes;

    public ListeFormes ()
//structure dedié a la recherche rapidefait pas le tour de tous les elements ...
//permet, si tu connais clé , retourne objet rattaché a la clé asap
    {
        listeFormes = new Hashtable();
        listeFormes.put("crayon", new TraceLibre()); //objets dans a completer
        listeFormes.put("effacer", new Efface());
        listeFormes.put("cercle", new Cercle());
        listeFormes.put("rectangle", new Rectangle());
    }

    public Forme recupererForme (String cle)
    {
        return this.listeFormes.get(cle);
    }


}
