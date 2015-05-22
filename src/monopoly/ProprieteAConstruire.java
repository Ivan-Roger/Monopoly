package monopoly;

public class ProprieteAConstruire extends CarreauPropriete {

    private int nbMaisons = 0;
    private int nbHotels = 0;
    private int[] loyerMaison;
    private Groupe groupePropriete;

    public ProprieteAConstruire(int numero, String nomCarreau, Monopoly monopoly, int prixAchat, int loyerBase) {
        super(numero, nomCarreau, monopoly, prixAchat, loyerBase);
    }

    @Override
    public void achatPropriete() {
        throw new UnsupportedOperationException();
    }

    public String getProprietaire(Joueur joueur) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int calculLoyer() {
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
