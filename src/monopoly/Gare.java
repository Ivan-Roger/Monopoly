package monopoly;

public class Gare extends CarreauPropriete {

    private final int loyerBase = 25;

    public Gare(int numero, String nomCarreau, Monopoly monopoly, int prixAchat) {
        super(numero, nomCarreau, monopoly, prixAchat);
    }

    @Override
    public int calculLoyer() {
        monopoly.inter.afficher("Debug : "+loyerBase + " - " + proprietaire.getNom());          // DEBUG !!!
        return loyerBase * this.proprietaire.getNbGares();
    }

    @Override
    public void achatPropriete(Joueur j) {
        if (this.prixAchat > j.getCash()) {
            this.monopoly.inter.afficher("Vous ne possédez pas assez d'argent pour acheter cette gare");
            this.monopoly.inter.afficher("Il vous manque " + (this.prixAchat - j.getCash()) + "€ pour effectuer cette action");
        } else {
            this.monopoly.inter.afficherGare(this);
            this.monopoly.inter.afficher("Voulez-vous acheter cette gare ?");
            if (this.monopoly.inter.lireBoolean()) {
                this.setProprietaire(j);
                j.addGare(this);
                j.payer(this.prixAchat);
                this.monopoly.inter.afficher("Vous venez d'acheter la gare.");
            }

        }
    }
}
