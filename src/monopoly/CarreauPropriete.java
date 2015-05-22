public abstract class CarreauPropriete extends Carreau {
	private int loyerBase;
	private int prixAchat;
	private Joueur proprietaire;

	public Joueur getProprietaire() {
		return this.proprietaire;
	}

	public int calculLoyer() {
		throw new UnsupportedOperationException();
	}

	public void achatPropriete() {
		throw new UnsupportedOperationException();
	}

	public int getPrixAchat() {
		return this.prixAchat;
	}

	private void setProprietaire(Joueur j) {
		throw new UnsupportedOperationException();
	}
}