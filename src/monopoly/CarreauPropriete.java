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
    
    
    @Override
    public void action(Joueur joueur) {
        if (this.proprietaire == null) {
            this.achatPropriete(joueur);
        } else {
            int var = joueur.payer(this.calculLoyer());
            monopoly.inter.afficher("Vous payez "+var+"€ a "+proprietaire.getNom());
            this.proprietaire.recevoirArgent(var);
        }
    }

}