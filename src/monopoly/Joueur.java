package monopoly;

import java.util.ArrayList;

public class Joueur {

    /* Attributs */
    private String nomJoueur;
    private int cash = 1500;
    private ArrayList<CarreauPropriete> proprietes;
    private Carreau position;
    private Monopoly monopoly;
    private boolean estEnPrison;
    private int nbDouble;

    /* Constructeur */
    // Changer le carreau de départ, plus en paramètre mais direct dans constructeur
    Joueur(String nom, Monopoly monopoly, Carreau c) {
        nomJoueur = nom;
        this.monopoly = monopoly;
        position = c;
        proprietes = new ArrayList<>();
    }

    /* getters */
    public String getNom() {
        return nomJoueur;
    }
    
    public Carreau getposition() {
        return position;
    }
            
    public int getCash() {
        return cash;
    }

    public int getNbDouble() {
        return nbDouble;
    }
    
    public boolean estEnPrison() {
        return estEnPrison;
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

    public ArrayList<CarreauPropriete> getProprietes() {
        return proprietes;
    }

    public ArrayList<Gare> getGares() {
        ArrayList<Gare> i = new ArrayList<>();
        for (CarreauPropriete c : proprietes) {
            if (c.getClass().getSimpleName() == "Gare") {
                i.add((Gare) c);
            }
        }
        return i;
    }

    public ArrayList<Compagnie> getCompagnies() {
        ArrayList<Compagnie> i = new ArrayList<>();
        for (CarreauPropriete c : proprietes) {
            if (c.getClass().getSimpleName() == "Compagnie") {
                i.add((Compagnie) c);
            }
        }
        return i;
    }

    /* Autres méthodes */
    public void addPropriete(CarreauPropriete c) {
        this.proprietes.add(c);
    }
    
    public void addNbDouble() {
        nbDouble++;
    }

    /* transferts d'argent */
    public void recevoirArgent(int l) {
        cash = cash + l;
    }

    public void payer(int l) {
        cash = cash - l;
    }

    /* Deplacement */
    // Voir comment changer la position par rapport au numéro du carreau
    public Carreau avancer(int jet) {
        throw new UnsupportedOperationException();
    }

    public void allerEnPrison() {
        estEnPrison = true;
        setPosition(monopoly.carreauPrison);
    }
    
    public void sortirPrison() {
        estEnPrison = false;
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

}
