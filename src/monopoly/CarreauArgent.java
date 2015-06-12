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
        monopoly.inter.afficherPosition(this,j);
        j.recevoirArgent(montant);
    }
    
    @Override
    public String toString() {
        return super.toString() + (montant!=0 ? " ("+montant+")" : "");
    }
}
