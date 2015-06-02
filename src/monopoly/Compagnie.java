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
        this.setProprietaire(j);
        j.addCompagnie(this);
        j.payer(this.prixAchat);
        this.monopoly.inter.afficher("Vous venez d'acheter la compagnie.");
    }
}
