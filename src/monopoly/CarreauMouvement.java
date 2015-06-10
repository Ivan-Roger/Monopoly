package monopoly;

public class CarreauMouvement extends CarreauAction {

    public CarreauMouvement(int numero, String nomCarreau, Monopoly monopoly) {
        super(numero, nomCarreau, monopoly);
    }

    /**
     * Menu CarreauMouvement, le joueur peut soit abandonner soit terminer le tour en prison
    **/
    @Override
    public void action(Joueur j) {
        monopoly.inter.afficherCarreauMouvement(this,j);
        this.monopoly.inter.afficher("vous tombez sur la case \"Allez en Prison\" ...");
        j.allerEnPrison();
        
        monopoly.inter.menuGeneral(j);
    }
}
