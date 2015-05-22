package monopoly;


import java.util.ArrayList;

public class Joueur {
        
        /* Attributs */
	private String nomJoueur;
	private int cash = 1500;
	private ArrayList<CarreauPropriete> proprietes;
	private Carreau position;
	private Monopoly monopoly;
        
        /* Constructeur */
        // Changer le carreau de départ, plus en paramètre mais direct dans constructeur
        Joueur (String nom, Monopoly monopoly, Carreau c) {
            nomJoueur = nom;
            this.monopoly = monopoly;
            position = c;
            proprietes = new ArrayList<>();
        }
        
        
        /* getters */

        public int getCash() {
            return cash;
        }
        
        public int getNbGares() {
            int i=0;
            for (CarreauPropriete c : proprietes) {
                if (c.getClass().getSimpleName() == "Gare") {
                    i++;
                }
            }
            return i;
        }
        
        public int getNbCompagnies() {
            int i=0;
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
            ArrayList<Gare> i= new ArrayList<>();
            for (CarreauPropriete c : proprietes) {
                if (c.getClass().getSimpleName() == "Gare") {
                    i.add((Gare)c);
                }
            }
            return i;
        }
        
        public ArrayList<Compagnie> getCompagnies() {
            ArrayList<Compagnie> i= new ArrayList<>();
            for (CarreauPropriete c : proprietes) {
                if (c.getClass().getSimpleName() == "Compagnie") {
                    i.add((Compagnie)c);
                }
            }
            return i;
        }
        
        
        /* Autres méthodes */
        public void addPropriete(CarreauPropriete c) {
            this.proprietes.add(c);
        }
       
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