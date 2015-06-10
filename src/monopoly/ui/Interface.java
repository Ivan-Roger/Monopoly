/*
 * Default License :
 * ...
 */

package monopoly.ui;

import java.util.ArrayList;
import monopoly.CarreauArgent;
import monopoly.CarreauPropriete;
import monopoly.CarreauTirage;
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
    
    /**
     * Initialisation des infos sur les joueurs.
     * @param j Tableau de joueurs.
     */
    public abstract void initInfosJoueurs(ArrayList<Joueur> j);
    
    /**
     * Lecture d'un entier entre les valeurs definies. Saisie verifié.
     * @param min Valeur minimale
     * @param max Valeur maximale
     * @return L'entier saisi
     */
    public abstract int lireInt(int min, int max);
    /**
     * Lecture d'un boolean (oui/non).
     * @return (Oui - True / Non - False)
     */
    public abstract boolean lireBoolean();
    /**
     * Lecture d'un saisie libre de texte. Non limité en longueur.
     * @return Le texte saisi
     */
    public abstract String lireString();
    
    /**
     * Affichage général. A utiliser lorsque l'information n'est pas recurente.
     * @param message Le message a afficher.
     */
    public abstract void afficher(String message);
    
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
     * Affichage des info d'une proprieté. Propriete
     * @param p La Proprieté a decrire
     */
    public abstract void afficherCarreauTirage(CarreauTirage c);
    /**
     * Affichage des info d'une proprieté. Compagnie, Gare ou Propriete
     * @param c La propriete a decrire.
     */
    public void afficherPropriete(CarreauPropriete c) {
        if (c instanceof Gare) {
            afficherPropriete((Gare)c);
        } else if (c instanceof Compagnie) {
            afficherPropriete((Compagnie)c);
        } else if (c instanceof ProprieteAConstruire) {
            afficherPropriete((ProprieteAConstruire)c);
        }
    }
    /**
     * Affichage des info d'une proprieté. Propriete
     * @param p La Proprieté a decrire
     */
    public abstract void afficherPropriete(ProprieteAConstruire p);
    /**
     * Affichage des info d'une Compagnie.
     * @param p La Proprieté a decrire
     */
    public abstract void afficherPropriete(Compagnie c);
    /**
     * Affichage des info d'une Gare.
     * @param p La Proprieté a decrire
     */
    public abstract void afficherPropriete(Gare g);
    /**
     * Affichage d'un lancer de dés.
     * @param lancer Le lancer composé de deux dés sur 6.
     */
    public abstract void afficherLancerDes(int[] lancer);
    /**
     * Affichage d'un carreauArgent.
     * @param c Le carreau a decrire
     */
    public abstract void afficherCarreauArgent(CarreauArgent c);
    /**
     * Affichage des maisons et hotels sur un terrain.
     * @param g Le terrain a decrire.
     */
    public abstract void afficherEtatConstructions(Groupe g);
    
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
    public abstract void menuLoyer(CarreauPropriete c, Joueur joueur);
    /**
     * Menu lors de lavisite du proprietaire sur son terrain.
     * @param c Le terrain.
     */
    public abstract void menuMaison(CarreauPropriete c);
    /**
     * Menu general.
     * @param j Le joueur
     */
    public abstract void menuGeneral(Joueur j);
    /**
     * Menu lors de l'arrivée sur un carreau argent.
     * @param j Le visiteur.
     * @param montant Le montant du carreau.
     */
    public abstract void menuArgent(Joueur j, int montant);
}
