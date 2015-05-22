package monopoly;

public class Joueur {
	private String nomJoueur;
	private int cash = 1500;
	private java.util.ArrayList<ProprieteAConstruire> proprietes;
	private Carreau positionCourante;
	private java.util.ArrayList<Gare> gares;
	private Monopoly monopoly;
	private java.util.ArrayList<Compagnie> compagnies;

	public void recevoirLoyer(int l) {
		throw new UnsupportedOperationException();
	}

	public void payerLoyer(int l) {
		throw new UnsupportedOperationException();
	}

	public Carreau avancer(int jet) {
		throw new UnsupportedOperationException();
	}

	public void payer() {
		throw new UnsupportedOperationException();
	}

	public void afficherInfosJoueur(Joueur j) {
		throw new UnsupportedOperationException();
	}

	public void afficher() {
		throw new UnsupportedOperationException();
	}

	public int getNbGares() {
		throw new UnsupportedOperationException();
	}

	public int getCash() {
		return this.cash;
	}

	public void addPropriete(CarreauPropriete c) {
		throw new UnsupportedOperationException();
	}
}