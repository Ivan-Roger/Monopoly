package monopoly;

public class CarreauArgent extends CarreauAction {

    private int montant;

    public CarreauArgent(int numero, String nomCarreau, Monopoly monopoly, int montant) {
        super(numero, nomCarreau, monopoly);
        this.montant = montant;
    }

    public int getMontant() {
        return montant;
    }
    
    /**
     * Menu CarreauArgent, Le joueur peut soit abandonner soit payer et terminer le tour.
     * @param j Le joueur tombant sur le carreau.
    **/ 
    @Override
    public void action(Joueur j) {
        monopoly.inter.afficherCarreauArgent(this,j);
        monopoly.inter.menuArgent(j, montant);
    }
}
