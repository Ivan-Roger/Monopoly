package monopoly;


import java.util.ArrayList;

public class Joueur {
        
        /* Attributs */
	private String nomJoueur;
	private int cash = 1500;
	private ArrayList<ProprieteAConstruire> proprietes;
	private Carreau position;
	private ArrayList<Gare> gares;
	private Monopoly monopoly;
	private ArrayList<Compagnie> compagnies;
        
        /* Constructeur */
        // Changer le carreau de départ, plus en paramètre mais direct dans constructeur
        Joueur (String nom, Monopoly monopoly, Carreau c) {
            nomJoueur = nom;
            this.monopoly = monopoly;
            position = c;
            proprietes = new ArrayList<>();
            gares = new ArrayList<>();
            compagnies = new ArrayList<>();
        }
        
        
        /* getters */

        public int getCash() {
            return cash;
        }
        
        public int getNbGares() {
            return gares.size();
        }
        
        public int getNbCompagnies() {
            return compagnies.size();
        }
        
        public ArrayList<ProprieteAConstruire> getProprietes() {
            return proprietes;
        }
        
        public ArrayList<Gare> getGares() {
            return gares;
        }
        
        public ArrayList<Compagnie> getCompagnies() {
            return compagnies;
        }
        
        
        /* Autres méthodes */
        
       
        /* transferts d'argent */
	public void recevoirLoyer(int l) {
		cash = cash + l;
	}

	public void payerLoyer(int l) {
		cash = cash - l;
	}
        
	public void payer(int l) {
		cash = cash - l;
	}
        
        /* Deplacement */
        // Voir comment changer la position par rapport au numéro du carreau
	public Carreau avancer(int jet) {
                throw new UnsupportedOperationException();
	}


        /* Affichage */
       	public void afficherInfosJoueur(Joueur j) {
		throw new UnsupportedOperationException();
	}

	public void afficher() {
		throw new UnsupportedOperationException();
	}

}