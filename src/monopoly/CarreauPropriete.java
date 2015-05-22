package monopoly;

public abstract class CarreauPropriete extends Carreau {

    protected final int prixAchat;
    protected Joueur proprietaire;

    public CarreauPropriete(int numero, String nomCarreau, Monopoly monopoly, int prixAchat) {
        super(numero, nomCarreau, monopoly);
        this.prixAchat = prixAchat;
    }

    public Joueur getProprietaire() {
        return this.proprietaire;
    }

    protected void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }

    public abstract int calculLoyer(Joueur j);

    public int getPrixAchat() {
        return prixAchat;
    }

    public abstract void achatPropriete(Joueur joueur);

}