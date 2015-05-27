package monopoly;

public class CarreauTirage extends CarreauAction {

    public CarreauTirage(int numero, String nomCarreau, Monopoly monopoly) {
        super(numero, nomCarreau, monopoly);
    }

    @Override
    public void action(Joueur j) {
        super.monopoly.inter.afficher("Carreau Tirage - Pas encore fonctinnel");
    }
}
