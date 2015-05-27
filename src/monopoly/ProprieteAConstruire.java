package monopoly;

public class ProprieteAConstruire extends CarreauPropriete {

    private int nbMaisons = 0;
    private int nbHotels = 0;
    private int[] loyers;
    private Groupe groupePropriete;

    public ProprieteAConstruire(int numero, String nomCarreau, Monopoly monopoly, int prixAchat, Groupe g, int[] loyers) {
        super(numero, nomCarreau, monopoly, prixAchat);
        this.loyers = loyers;
        this.groupePropriete = g;
    }

    @Override
    public void achatPropriete(Joueur joueur) {

        if (this.prixAchat > joueur.getCash()) {
            this.monopoly.inter.afficher("Vous ne possédez pas assez d'argent pour acheter cette propriété");
            this.monopoly.inter.afficher("Il vous manque " + (this.prixAchat - joueur.getCash()) + "€ pour effectuer cette action");
        } else {
            this.monopoly.inter.afficherPropriete(this);
            this.monopoly.inter.afficher("Voulez-vous acheter cette propriété ?");
            if (this.monopoly.inter.lireBoolean()) {
                this.setProprietaire(joueur);
                joueur.addPropriete(this);
                joueur.payer(this.prixAchat);
                this.monopoly.inter.afficher("Vous venez d'acheter le terrain.");
            }

        }
    }

    @Override
    public int calculLoyer() {
        if (this.nbHotels < 1) {
            return this.loyers[this.nbMaisons];
        } else {
            return this.loyers[5];
        }

    }

    public int getNbMaisons() {
        return this.nbMaisons;
    }

    public int getNbHotels() {
        return this.nbHotels;
    }

    public Groupe getGroupe() {
        return this.groupePropriete;
    }
}
