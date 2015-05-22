package monopoly;

public class Gare extends CarreauPropriete {

    private int loyerBase = 25;

    public Gare(int numero, String nomCarreau, Monopoly monopoly, int prixAchat) {
        super(numero, nomCarreau, monopoly, prixAchat);
    }

    public void action() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int calculLoyer(Joueur j) {
        return loyerBase * j.getNbGares();
    }

    @Override
    public void achatPropriete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
