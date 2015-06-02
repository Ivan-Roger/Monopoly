package monopoly;

public class Gare extends CarreauPropriete {

    private final int loyerBase = 25;

    public Gare(int numero, String nomCarreau, Monopoly monopoly, int prixAchat) {
        super(numero, nomCarreau, monopoly, prixAchat);
    }

    @Override
    public int calculLoyer() {
        monopoly.inter.afficher("Debug : " + loyerBase + " - " + proprietaire.getNom());          // DEBUG !!!
        return loyerBase * this.proprietaire.getNbGares();
    }

    @Override
    public void achatPropriete(Joueur j) {
        this.setProprietaire(j);
        j.addGare(this);
        j.payer(prixAchat);
        this.monopoly.inter.afficher("Vous venez d'acheter la gare.");
    }
}
