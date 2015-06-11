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
    
    public ArrayList<ProprieteAConstruire> getProprietes() {
        return proprietes;
    }

    public int getPrixAchatMaison() {
        return prixAchatMaison;
    }

    public int getPrixAchatHotel() {
        return prixAchatHotel;
    }
    
    public int getNbMaisonsGroupe() {
        int tmp = 0;
        for(ProprieteAConstruire p : proprietes) {
            tmp = tmp + p.getNbMaisons();
        }
        return tmp;
    }
    
    public int getNbHotelsGroupe() {
        int tmp = 0;
        for(ProprieteAConstruire p : proprietes) {
            tmp = tmp + p.getNbHotels();
        }
        return tmp;
    }
    
    public int getNbProprietes() {
        int tmp = 0;
        for(ProprieteAConstruire p : proprietes) {
            tmp++;
        }
        return tmp;
    }

    public void setPrixAchatMaison(int prix) {
        this.prixAchatMaison = prix;
    }

    public void setPrixAchatHotel(int prix) {
        this.prixAchatHotel = prix;
    }
}
