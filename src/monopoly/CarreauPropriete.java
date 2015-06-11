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

    public abstract int calculLoyer();

    public int getPrixAchat() {
        return prixAchat;
    }

    public abstract void achatPropriete(Joueur joueur);

    /**
     * Menu CarreauPropriete Si la propriété n'a pas de propriétaire 
     * Si le joueur a moins d'argent que le prix de la propriété 
     * il peut soit abandonner soit terminer le tour 
     * Ou si le joueur a plus d'argent que le prix de la propriété
     * il peut soit l'acheter soit abandonner soit terminer le tour 
     * Si la propriété a un propriétaire et si ce n'est pas le joueur alors
     * il peut soit abandonner soit payer et terminer le tour Sinon il peut soit
     * abandonner soit terminer le tour
     *
     * @param joueur le joueur atterrissant sur le carreau.
     *
     */
    @Override
    public void action(Joueur joueur) {
        if (this.proprietaire == null) {
            monopoly.inter.menuAchatPropriete(this, joueur);
        } else {
            if (this.proprietaire != joueur) {
                monopoly.inter.menuLoyer(this, joueur);
            } else {
                monopoly.inter.menuMaison(this);
            }
        }
    }

}
