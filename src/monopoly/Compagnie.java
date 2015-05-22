package monopoly;

public class Compagnie extends CarreauPropriete {

    public Compagnie(int numero, String nomCarreau, Monopoly monopoly, int prixAchat, int loyerBase) {
        super(numero, nomCarreau, monopoly, prixAchat, loyerBase);
    }

    public void action() {
        throw new UnsupportedOperationException();
    }

    public int calculLoyer(int tot) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int calculLoyer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
