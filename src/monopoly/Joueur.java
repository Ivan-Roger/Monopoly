package monopoly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Joueur {

    /* Attributs */
    private String nomJoueur;
    private int cash = 1500;
    private ArrayList<ProprieteAConstruire> proprietes;
    private ArrayList<Gare> gares;
    private ArrayList<Compagnie> compagnies;
    private LinkedList<CarteLiberePrison> liberation;
    private Carreau position;
    private Monopoly monopoly;
    private int tempsPrison = -1;
    private int nbDouble;
    private boolean abandonne = false;

    /* Constructeur */
    // Changer le carreau de départ, plus en paramètre mais direct dans constructeur
    Joueur(String nom, Monopoly monopoly) {
        nomJoueur = nom;
        this.monopoly = monopoly;
        position = monopoly.carreauDepart;
        proprietes = new ArrayList<>();
        gares = new ArrayList<>();
        compagnies = new ArrayList<>();
        liberation = new LinkedList<CarteLiberePrison>();
    }

    public void addCarteLiberation(CarteLiberePrison c) {
        liberation.add(c);
    }
    
    public int getNbLiberation(){
        return liberation.size();
    }
    
    public CarteLiberePrison removeCarteLiberation() {
        return liberation.poll();
    }
    
    /* getters */
    public String getNom() {
        return nomJoueur;
    }
    
    public Carreau getPosition() {
        return position;
    }
            
    public int getCash() {
        return cash;
    }

    public int getNbDouble() {
        return nbDouble;
    }
    
    public int getNbMaisons() {
        int res=0;
        for (ProprieteAConstruire p : proprietes) {
            res+=p.getNbMaisons();
        }
        return res;
    }
    
    public int getNbHotels() {
        int res=0;
        for (ProprieteAConstruire p : proprietes) {
            res+=p.getNbHotels();
        }
        return res;
    }
    
    public boolean estEnPrison() {
        return tempsPrison>-1;
    }
    
    public int getTempsPrison() {
        return tempsPrison;
    }

    public void abandonner() {
        monopoly.retirerJoueur(this);
        this.monopoly.inter.afficher("Vous avez abandonné");
        abandonne = true;
    }
    
    public boolean abandonne() {
        return abandonne;
    }
    
    public int getNbGares() {
        int i = 0;
        for (CarreauPropriete c : proprietes) {
            if (c.getClass().getSimpleName() == "Gare") {
                i++;
            }
        }
        return i;
    }

    public int getNbCompagnies() {
        int i = 0;
        for (CarreauPropriete c : proprietes) {
            if (c.getClass().getSimpleName() == "Compagnie") {
                i++;
            }
        }
        return i;
    }

    public ArrayList<ProprieteAConstruire> getProprietes() {
        Collections.sort(proprietes, new Comparator<ProprieteAConstruire>() {

            @Override
            public int compare(ProprieteAConstruire o1, ProprieteAConstruire o2) {
                return o1.getGroupe().getCouleur().compareTo(o2.getGroupe().getCouleur());
            }
        
    });
        return proprietes;
    }

    public ArrayList<Gare> getGares() {
        return gares;
    }

    public ArrayList<Compagnie> getCompagnies() {
        return compagnies;
    }
    

    /* Autres méthodes */
    public void addPropriete(ProprieteAConstruire c) {
        proprietes.add(c);
    }
    public void addGare(Gare c) {
        gares.add(c);
    }
    public void addCompagnie(Compagnie c) {
        compagnies.add(c);
    }
    
    public void addNbDouble() {
        nbDouble++;
    }

    /* transferts d'argent */
    public void recevoirArgent(int l) {
        cash = cash + l;
    }

    public int payer(int l) {
        int res;
        if (cash-l<0) {
            res=cash;
            cash = 0;
        } else {
            res=l;
            cash=cash-l;
        }
        return res;
    }

    /* Deplacement */

    public void allerEnPrison() {
        tempsPrison = 0;
        setPosition(monopoly.carreauPrison);
    }
    
    public void addTempsPrison() {
        tempsPrison++;
    }
    
    public void sortirPrison() {
        tempsPrison = -1;
    }
            
    public void setPosition(Carreau c) {
        position = c;
    }
    
    
    /* Affichage */
    public void afficherInfosJoueur(Joueur j) {
        throw new UnsupportedOperationException();
    }

    public void afficher() {
        throw new UnsupportedOperationException();
    }

    public Carreau getCarreau() {
        return position;
    }

    void resetNbDouble() {
        this.nbDouble=0;
    }

}
