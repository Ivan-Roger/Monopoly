package monopoly;

public class ProprieteAConstruire extends CarreauPropriete {

    private int nbMaisons = 0;
    private int nbHotels = 0;
    private int[] loyers;
    private Groupe groupePropriete;

    public ProprieteAConstruire(int numero, String nomCarreau, Monopoly monopoly, int prixAchat, int[] loyers) {
        super(numero, nomCarreau, monopoly, prixAchat);
        this.loyers=loyers;
    }

    @Override
    public void achatPropriete() {
        throw new UnsupportedOperationException();
    }

    public String getProprietaire(Joueur joueur) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int calculLoyer(Joueur j) {
        throw new UnsupportedOperationException();
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
}
