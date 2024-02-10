package com.example.appcafbuckstar;

import java.util.Hashtable;

public class ListeProduits {

    private Hashtable<String, Produit> liste;

    public ListeProduits ()
//structure dedié a la recherche rapidefait pas le tour de tous les elements ...
//permet, si tu connais clé , retourne objet rattaché a la clé asap
    {
        liste = new Hashtable();
        liste.put("Café filtre Petit", new CafeFiltre("Petit" )); //objets dans a completer
        liste.put("Café filtre Moyen", new CafeFiltre("Moyen"));
        liste.put("Café filtre Grand", new CafeFiltre("Grand"));
        liste.put("Americano Petit", new Americano("Petit"));
        liste.put("Americano Moyen", new Americano("Moyen"));
        liste.put("Americano Grand", new Americano("Grand"));
        liste.put("Café glacé Petit", new CafeGlace("Petit"));
        liste.put("Café glacé Moyen", new CafeGlace("Moyen"));
        liste.put("Café glacé Grand", new CafeGlace("Grand"));
        liste.put("Latté Petit", new Latte("Petit"));
        liste.put("Latté Moyen", new Latte("Moyen"));
        liste.put("Latté Grand", new Latte("Grand"));
    }

    public Produit recupererProduit ( String cle)
    {
        return liste.get(cle);
    }


}
