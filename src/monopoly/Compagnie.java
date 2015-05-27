package monopoly;

public class Compagnie extends CarreauPropriete {

    public Compagnie(int numero, String nomCarreau, Monopoly monopoly, int prixAchat) {
        super(numero, nomCarreau, monopoly, prixAchat);
    }

    @Override
    public int calculLoyer() {
        if (this.proprietaire.getNbCompagnies() == 2) {

            return this.monopoly.calculTotalDes(this.monopoly.jetDeDes()) * 10;
        } else {
            return this.monopoly.calculTotalDes(this.monopoly.jetDeDes()) * 4;
        }

    }

    @Override
    public void achatPropriete(Joueur j) {
        if (this.prixAchat > j.getCash()) {
            this.monopoly.inter.afficher("Vous ne possédez pas assez d'argent pour acheter cette compagnie");
            this.monopoly.inter.afficher("Il vous manque " + (this.prixAchat - j.getCash()) + "€ pour effectuer cette action");
        } else {
            this.monopoly.inter.afficherCompagnie(this);
            this.monopoly.inter.afficher("Voulez-vous acheter cette compagnie ?");
            if (this.monopoly.inter.lireBoolean()) {
                this.setProprietaire(j);
                j.addCompagnie(this);
                j.payer(this.prixAchat);
                this.monopoly.inter.afficher("Vous venez d'acheter la compagnie.");
            }

        }
    }
}
