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
            if (this.prixAchat > joueur.getCash()) {
                this.monopoly.inter.afficher("Vous ne possédez pas assez d'argent pour acheter.");
                this.monopoly.inter.afficher("Il vous manque " + (this.prixAchat - joueur.getCash()) + "€");
            } else {
                if (this instanceof Gare) {
                    this.monopoly.inter.afficherGare((Gare) this);
                    this.monopoly.inter.afficher("  1) Acheter cette gare");
                } else if (this instanceof Compagnie) {
                    this.monopoly.inter.afficherCompagnie((Compagnie) this);
                    this.monopoly.inter.afficher("  1) Acheter cette compagnie");
                } else if (this instanceof ProprieteAConstruire) {
                    this.monopoly.inter.afficherPropriete((ProprieteAConstruire) this);
                    this.monopoly.inter.afficher("  1) Acheter cette proprieté");
                }
                this.monopoly.inter.afficher("  2) Abandonner");
                this.monopoly.inter.afficher("  3) Terminer votre tour");
                switch (this.monopoly.inter.lireInt(1, 3)) {
                    case 1:
                        this.achatPropriete(joueur);
                        break;
                    case 2:
                        joueur.abandonner();
                        break;
                    default:
                        break;
                }
            }
        } else {
            if (this.proprietaire != joueur) {
                int var = joueur.payer(this.calculLoyer());
                monopoly.inter.afficher("Vous payez " + var + "€ a " + proprietaire.getNom());
                this.proprietaire.recevoirArgent(var);
            }
        }
    }

}
