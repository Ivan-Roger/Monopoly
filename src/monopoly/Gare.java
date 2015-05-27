package monopoly;

public class Gare extends CarreauPropriete {

    private final int loyerBase = 25;

    public Gare(int numero, String nomCarreau, Monopoly monopoly, int prixAchat) {
        super(numero, nomCarreau, monopoly, prixAchat);
    }

    @Override
    public int calculLoyer() {
        return loyerBase * this.proprietaire.getNbGares();
    }

    @Override
    public void achatPropriete(Joueur j) {
        if (this.prixAchat > j.getCash()) {
            this.monopoly.inter.afficher("Vous ne possédez pas assez d'argent pour acheter cette gare");
            this.monopoly.inter.afficher("Il vous manque " + (this.prixAchat - j.getCash()) + " euros pour effectuer cette action");
        } else {
            this.monopoly.inter.afficherGare(this);
            this.monopoly.inter.afficher("Voulez-vous acheter cette gare ?");
            Boolean b = this.monopoly.inter.lireBoolean();
            if (b == true) {
                this.setProprietaire(j);
                j.addGare(this);
                j.payer(this.prixAchat);
                this.monopoly.inter.afficher("Vous venez d'acheter la gare.");
            }

        }
    }

    @Override
    public void action(Joueur joueur) {
        if (this.proprietaire == null) {
            this.achatPropriete(joueur);
        } else {
            joueur.payer(this.calculLoyer());
            this.proprietaire.recevoirArgent(this.calculLoyer());
        }
    }
}
