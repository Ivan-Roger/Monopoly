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
     * Menu CarreauArgent, Le joueur peut soit abandonner soit payer et terminer le tour  
    **/
 
    @Override
    public void action(Joueur j) {
        monopoly.inter.afficherCarreauArgent(this);
        j.recevoirArgent(montant);
        this.monopoly.inter.afficher("  1) Abandonner");
        if (montant<0) {
            this.monopoly.inter.afficher("  2) Payer "+(montant*-1)+"€ et terminer votre tour");
        } else if (montant>0) {
            this.monopoly.inter.afficher("  2) Recevoir "+montant+"€ et terminer votre tour");
        } else {
            this.monopoly.inter.afficher("  2) Terminer votre tour");
        }
        switch (this.monopoly.inter.lireInt(1, 2)) {
            case 1:
                j.abandonner();
                break;
            default:
                break;
        }
    }
}
