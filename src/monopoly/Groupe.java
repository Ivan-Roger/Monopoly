package monopoly;

import java.util.ArrayList;

public class Groupe {

    private int prixAchatMaison;
    private int prixAchatHotel;
    private CouleurPropriete couleur;
    private ArrayList<ProprieteAConstruire> proprietes;

    public Groupe(CouleurPropriete c) {
        couleur = c;
        proprietes = new ArrayList<ProprieteAConstruire>();
    }

    public CouleurPropriete getCouleur() {
        return this.couleur;
    }

    public void addPropriete(ProprieteAConstruire p) {
        proprietes.add(p);
    }
}
