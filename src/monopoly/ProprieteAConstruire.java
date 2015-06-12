package monopoly;

public class ProprieteAConstruire extends CarreauPropriete {

    /* Attributs */
    private int nbMaisons = 0;
    private int nbHotels = 0;
    private int[] loyers;
    private Groupe groupePropriete;

    /* Constructeur */
    public ProprieteAConstruire(int numero, String nomCarreau, Monopoly monopoly, int prixAchat, Groupe g, int[] loyers) {
        super(numero, nomCarreau, monopoly, prixAchat);
        this.loyers = loyers;
        groupePropriete = g;
    }

    /* Réecriture des méthodes super */
    @Override
    public void achatPropriete(Joueur joueur) {
        setProprietaire(joueur);
        joueur.addPropriete(this);
        joueur.payer(prixAchat);
        monopoly.inter.info("Vous venez d'acheter le terrain.");
    }

    @Override
    public int calculLoyer() {
        if (nbHotels < monopoly.getNbHotelsMax()) {
            return loyers[nbMaisons];
        } else {
            return loyers[monopoly.getNbMaisonsMax() + nbHotels];
        }

    }

    /* Getters */
    public int getNbMaisons() {
        return nbMaisons;
    }

    public int getNbHotels() {
        return nbHotels;
    }

    public Groupe getGroupe() {
        return groupePropriete;
    }

    /* Autres Méthodes */
    public void addMaisons(int m) {
        nbMaisons = nbMaisons + m;
    }

    public void addHotels(int h) {
        nbHotels = nbHotels + h;
    }

    @Override
    public String toString() {
        return super.toString() + " - Groupe " + this.getGroupe().getCouleur() + this.getGroupe().getCouleur().name() + ((char) 27 + "[0m") + (nbMaisons > 0 ? " - " + nbMaisons + " Maisons" : "") + (nbHotels > 0 ? " - " + nbHotels + " Hotels" : "");
    }

    public void construire(int nb) {
        if (nb > monopoly.getNbMaisonsMax()) { // Si nb > nbMaisonsMax alors hotel
            nbHotels += (nb - monopoly.getNbMaisonsMax());
        } else { // Sinon maison
            nbMaisons += nb;
        }
    }

}
