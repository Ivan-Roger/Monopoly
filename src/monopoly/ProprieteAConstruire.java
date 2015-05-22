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

    @Override
    public int calculLoyer(Joueur j) {
        return 0;
    }

    public void setNbMaisons(int nb) {
        throw new UnsupportedOperationException();
    }

    public void setHotel(int hot) {
        throw new UnsupportedOperationException();
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
