package monopoly;

public abstract class CarreauPropriete extends Carreau {

    private final int loyerBase;
    private final int prixAchat;
    private Joueur proprietaire;

    public CarreauPropriete(int numero, String nomCarreau, Monopoly monopoly, int prixAchat, int loyerBase) {
        super(numero, nomCarreau, monopoly);
        this.prixAchat = prixAchat;
        this.loyerBase = loyerBase;
    }

    public Joueur getProprietaire() {
        return this.proprietaire;
    }

    private void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }

    public abstract int calculLoyer();

    public int getPrixAchat() {
        return prixAchat;
    }

    public void achatPropriete() {
        this.monopoly.inter.afficher("Voulez-vous acheter cette propriété ?");
        Boolean b = this.monopoly.inter.lireBoolean();

        if (b == true) {
            if (this.prixAchat > this.proprietaire.getCash()) {
                this.monopoly.inter.afficher("Vous ne possédez pas assez d'argent pour acheter cette propriété");
                this.monopoly.inter.afficher("Il vous manque " + (this.prixAchat - this.proprietaire.getCash()) + " euros pour effectuer cette action");
            } else {
                this.monopoly.inter.afficher("Nom : "  + this.nomCarreau + "   " + "Groupe : " + this.getGroupe());
                this.monopoly.inter.afficher("");
            }
        }
    }

}