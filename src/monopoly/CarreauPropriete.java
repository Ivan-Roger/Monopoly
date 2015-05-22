package monopoly;

public abstract class CarreauPropriete extends Carreau {

    private final int prixAchat;
    private Joueur proprietaire;

    public CarreauPropriete(int numero, String nomCarreau, Monopoly monopoly, int prixAchat) {
        super(numero, nomCarreau, monopoly);
        this.prixAchat = prixAchat;
    }

    public Joueur getProprietaire() {
        return this.proprietaire;
    }

    private void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }

    public abstract int calculLoyer(Joueur j);

    public void achatPropriete() {

    }
}
