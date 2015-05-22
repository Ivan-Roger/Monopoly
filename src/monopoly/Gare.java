package monopoly;

public class Gare extends CarreauPropriete {

    private Gare calculLoyer;

    public Gare(int numero, String nomCarreau, Monopoly monopoly, int prixAchat, int loyerBase) {
        super(numero, nomCarreau, monopoly, prixAchat, loyerBase);
    }

    public void action() {
        throw new UnsupportedOperationException();
    }

    public int calculLoyer(int nbGares) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int calculLoyer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
