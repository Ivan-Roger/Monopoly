/*
 * Default License :
 * ...
 */

package monopoly.ui;

import java.util.ArrayList;
import monopoly.Carreau;
import monopoly.CarreauArgent;
import monopoly.CarreauMouvement;
import monopoly.CarreauPropriete;
import monopoly.CarreauTirage;
import monopoly.Carte;
import monopoly.Compagnie;
import monopoly.Gare;
import monopoly.Groupe;
import monopoly.Joueur;
import monopoly.Monopoly;
import monopoly.ProprieteAConstruire;
import monopoly.ex.ConstruireException;

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
    
    /**
     * Initialisation des infos sur les joueurs.
     * @param j Tableau de joueurs.
     */
    public abstract void initInfosJoueurs(ArrayList<Joueur> j);
    
    
    /**
     * Affichage de toutes les infos sur un joueur. 
     * @param j Le joueur a decrire.
     */
    public abstract void afficherInfosJoueur(Joueur j);
    /**
     * Affichage du nouveau tour.
     * @param tour Le numero de tour.
     */
    public abstract void afficherInfosTour(int tour);
    /**
     * Affichage d'un lancer de dés.
     * @param lancer Le lancer composé de deux dés sur 6.
     */
    public abstract void afficherLancerDes(int[] lancer);
    /**
     * Affichage des maisons et hotels sur un terrain.
     * @param g Le terrain a decrire.
     */
    //public abstract void afficherConstructionsPossibles(int nbMaisonsConstructibles, int nbHotelConstructibles, Groupe g);
    
    //public abstract void afficherChoixMaisonsAConstruire(Groupe g);
    
    //public abstract void afficherRecapitulatifChoixMaisonsAConstruire(int[] nbMaisonsAConstruire, Groupe g, Joueur j);
    
    /**
     * Menu d'achat d'un terrain, gare ou compagnie.
     * @param c Le terrain
     * @param j l'acheteur
     */
    public abstract void menuAchatPropriete(CarreauPropriete c, Joueur j);
    /**
     * Menu de construction de maison ou d'hotel.
     * @param p 
     */
    public abstract void menuConstruire(ProprieteAConstruire p);
    /**
     * Menu pour payer le loyer lors de l'arrivée sur un terrain.
     * @param c Le terrain
     * @param joueur Le payeur
     */
    public abstract void payerLoyer(CarreauPropriete c, Joueur joueur);
    /**
     * Menu lors de lavisite du proprietaire sur son terrain.
     * @param c Le terrain.
     */
    public abstract void passageMaison(CarreauPropriete c);
    /**
     * Menu lors de l'arrivée sur un carreau argent.
     * @param j Le visiteur.
     * @param montant Le montant du carreau.
     */
    public abstract void passageArgent(Joueur j, int montant);

    /**
     * Attends une action de l'utilisateur pour continuer.
     */
    public abstract void pause();

    /**
     * Affichage général. A utiliser lorsque l'information n'est pas recurente.
     * @param message Le message a afficher.
     */
    public abstract void info(String message);

    public abstract void afficherPrison(Joueur j);

    public abstract void passageDepart();        

    public abstract void finTour(Joueur j);

    public abstract void afficherLiberation(String message);
    
    public abstract ArrayList<Joueur> saisieJoueurs();
    
    public abstract void choixModeDemo();

    public abstract void afficherPosition(Carreau c, Joueur j);

    public abstract void afficherCarte(Carte c, Joueur j);
    
    //public abstract void verifConstruire(ProprieteAConstruire p) throws ConstruireException;
    
    //public abstract void afficherEtatConstructions(Groupe g);

    public abstract int[] selectionConstruction(Groupe g, Joueur j);

}
