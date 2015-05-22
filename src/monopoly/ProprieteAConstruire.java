public class ProprieteAConstruire extends CarreauPropriete {
	private int nbMaisons = 0;
	private int nbHotels = 0;
	private java.util.ArrayList<int> loyerMaison;
	private Groupe groupePropriete;

	public void achatPropriété() {
		throw new UnsupportedOperationException();
	}

	public ArrayList<Propriété> getPropriétés() {
		throw new UnsupportedOperationException();
	}

	public String getProprietaire(Joueur joueur) {
		throw new UnsupportedOperationException();
	}

	public void calculLoyer() {
		throw new UnsupportedOperationException();
	}

	public void setNbMaisons(int nb) {
		throw new UnsupportedOperationException();
	}

	public void setHotel(int hot) {
		throw new UnsupportedOperationException();
	}

	public int getNbMaisons() {
		return this.nbMaisons;
	}

	public int getNbHotels() {
		return this.nbHotels;
	}
}