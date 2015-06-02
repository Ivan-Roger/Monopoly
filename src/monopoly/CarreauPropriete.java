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
                this.monopoly.inter.afficher("Vous ne possédez pas assez d'argent pour acheter cette propriété");
                this.monopoly.inter.afficher("Il vous manque " + (this.prixAchat - joueur.getCash()) + "€ pour effectuer cette action");
            } else {
                if (this instanceof Gare) {
                    this.monopoly.inter.afficherGare((Gare) this);
                    this.monopoly.inter.afficher("Voulez-vous acheter cette gare ?");
                } else if (this instanceof Compagnie) {
                    this.monopoly.inter.afficherCompagnie((Compagnie) this);
                    this.monopoly.inter.afficher("Voulez-vous acheter cette compagnie ?");
                } else if (this instanceof ProprieteAConstruire) {
                    this.monopoly.inter.afficherPropriete((ProprieteAConstruire) this);
                    this.monopoly.inter.afficher("Voulez-vous acheter cette proprieté ?");
                }
                if (this.monopoly.inter.lireBoolean()) {
                    this.achatPropriete(joueur);
                }
            }
            this.achatPropriete(joueur);
        } else {
            int var = joueur.payer(this.calculLoyer());
            monopoly.inter.afficher("Vous payez " + var + "€ a " + proprietaire.getNom());
            this.proprietaire.recevoirArgent(var);
        }
    }

}
