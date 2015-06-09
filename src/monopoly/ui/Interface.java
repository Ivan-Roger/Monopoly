/*
 * Default License :
 * ...
 */

package monopoly.ui;

import monopoly.CarreauArgent;
import monopoly.CarreauPropriete;
import monopoly.Compagnie;
import monopoly.Gare;
import monopoly.Groupe;
import monopoly.Joueur;
import monopoly.Monopoly;
import monopoly.ProprieteAConstruire;

/**
 * class : Interface
 * by    : rogeri
 * @author rogeri
 */
public abstract class Interface {

    /* Attributs */
    public Monopoly monopoly;
    
    public Interface(Monopoly monopoly){
        this.monopoly = monopoly;
    }
    
    public abstract int lireInt(int min, int max);
    public abstract boolean lireBoolean();
    public abstract String lireString();
    
    public abstract void afficher(String message);
    
    public abstract void afficherInfosJoueur(Joueur j);
    public abstract void afficherInfosTour(int tour);
    public abstract void afficherPropriete(CarreauPropriete c);
    public abstract void afficherPropriete(ProprieteAConstruire p);
    public abstract void afficherPropriete(Compagnie c);
    public abstract void afficherPropriete(Gare g);
    public abstract void afficherLancerDes(int[] lancer);
    public abstract void afficherCarreauArgent(CarreauArgent c);
    public abstract void afficherEtatConstructions(Groupe g);
    
    public abstract void menuAchatPropriete(CarreauPropriete c, Joueur j);
    public abstract void menuConstruire(ProprieteAConstruire p);
    public abstract void menuLoyer(CarreauPropriete c, Joueur joueur);
    public abstract void menuMaison(CarreauPropriete c);
    public abstract void menuGeneral(Joueur j);
    public abstract void menuArgent(Joueur j, int montant);
}
