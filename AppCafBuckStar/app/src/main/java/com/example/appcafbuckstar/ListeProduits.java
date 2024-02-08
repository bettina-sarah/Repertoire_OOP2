package com.example.appcafbuckstar;

import java.util.Hashtable;

public class ListeProduits {

    private Hashtable<String, Produit> liste;

    public ListeProduits ()
//structure dedié a la recherche rapidefait pas le tour de tous les elements ...
//permet, si tu connais clé , retourne objet rattaché a la clé asap
    {

        CafeFiltre petit = new CafeFiltre("Café filtre Petit", "Petit", 1.80, 5);

        liste = new Hashtable();
        liste.put("Café filtre Petit", new CafeFiltre("Café filtre Petit", "Petit", 1.80, 5) ); //objets dans a completer
        liste.put("Café filtre Moyen", new CafeFiltre("Café filtre Moyen", "Moyen", petit.getPrix()*5/3, 7)));
        liste.put("Café filtre Grand", /* à compléter*/);
        liste.put("Americano Petit",/* à compléter*/);
        liste.put("Americano Moyen", );
        liste.put("Americano Grand", );
        liste.put("Café glacé Petit", );
        liste.put("Café glacé Moyen", );
        liste.put("Café glacé Grand", );
        liste.put("Latté Petit", );
        liste.put("Latté Moyen", );
        liste.put("Latté Grand", );
    }

    public Produit recupererProduit ( String cle)
    {
       // à compléter
        return null;
    }


}
