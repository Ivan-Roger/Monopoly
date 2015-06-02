package monopoly;

public class CarreauTirage extends CarreauAction {
    private String type;
    
    public CarreauTirage(int numero, String nomCarreau, Monopoly monopoly, String type) {
        super(numero, nomCarreau, monopoly);
        this.type = type;
    }

    @Override
    public void action(Joueur j) {
        super.monopoly.inter.afficher("Carreau Tirage - Pas encore fonctinnel");
    }
}
