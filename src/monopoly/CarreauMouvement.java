package monopoly;

public class CarreauMouvement extends CarreauAction {

    public CarreauMouvement(int numero, String nomCarreau, Monopoly monopoly) {
        super(numero, nomCarreau, monopoly);
    }

    
     /**
      *  Menu CarreauMouvement, le joueur peut soit abandonner soit terminer le tour en prison
     * @param j
      */
    
    
    @Override
    public void action(Joueur j) {
        monopoly.inter.afficherPosition(this,j);
        j.allerEnPrison();
    }
}
