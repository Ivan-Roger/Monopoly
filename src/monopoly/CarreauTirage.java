package monopoly;

public class CarreauTirage extends CarreauAction {

    public CarreauTirage(int numero, String nomCarreau, Monopoly monopoly) {
        super(numero, nomCarreau, monopoly);
    }

    @Override
    public void action(Joueur j) {
        //communauté  3 - 18 - 34
        //chance      8 - 23 - 37
        super.monopoly.inter.afficher("Carreau Tirage - Pas encore fonctinnel");
        
        if (this.getId() == 8 || this.getId() == 23 || this.getId() == 37) {
            
        } else {
            
        }
    }
    
    
}
