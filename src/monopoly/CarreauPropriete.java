package monopoly;

public abstract class CarreauPropriete extends Carreau {
	private final int loyerBase;
	private final int prixAchat;
	private Joueur proprietaire;

        public CarreauPropriete(int numero, String nomCarreau, Monopoly monopoly,int prixAchat,int loyerBase) {
            super(numero,nomCarreau,monopoly);
            this.prixAchat = prixAchat;
            this.loyerBase = loyerBase;
        }
        
        
	public Joueur getProprietaire() {
		return this.proprietaire;
	}

        private void setProprietaire(Joueur proprietaire) {
            this.proprietaire = proprietaire;
        }

	public abstract int calculLoyer() ;

	public void achatPropriete() {
		
	}
}