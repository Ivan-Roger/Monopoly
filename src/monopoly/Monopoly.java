package monopoly;

import java.util.ArrayList;

public class Monopoly {
	private int nbMaisons = 32;
	private int nbHotels = 12;
	private Carreau carreaux;
	private ArrayList<Joueur> joueurs;
	public Interface interface_8;

	public int[] jetDeDes() {
		throw new UnsupportedOperationException();
	}

	public Joueur joueurSuivant() {
		throw new UnsupportedOperationException();
	}

	public int getNbMaison() {
		throw new UnsupportedOperationException();
	}

	public void afficherInfosTour(Joueur j, int jet, Carreau c) {
		throw new UnsupportedOperationException();
	}

	public int calculTotalDes(int[] des) {
		throw new UnsupportedOperationException();
	}

	public void saisir() {
		throw new UnsupportedOperationException();
	}

	public ArrayList<Joueur> getJoueurs() {
		throw new UnsupportedOperationException();
	}

	public void jouerUnCoup(Joueur j) {
		throw new UnsupportedOperationException();
	}

	public void lancerDésAvancer() {
		throw new UnsupportedOperationException();
	}

	public boolean demanderConstruction() {
		throw new UnsupportedOperationException();
	}

	public void achatMaison(int nb, int prix) {
		throw new UnsupportedOperationException();
	}

	public void achatHotel(int prix) {
		throw new UnsupportedOperationException();
	}

	public void setMaison(int nb) {
		throw new UnsupportedOperationException();
	}

	public boolean verifieAchat(int nbConstruction, int cashJoueur, int maisonBanque) {
		throw new UnsupportedOperationException();
	}

	public int getNbMaisons() {
		return this.nbMaisons;
	}

	public int getNbHotels() {
		return this.nbHotels;
	}
}