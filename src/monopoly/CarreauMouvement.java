package monopoly;

public class CarreauMouvement extends CarreauAction {

    public CarreauMouvement(int numero, String nomCarreau, Monopoly monopoly) {
        super(numero, nomCarreau, monopoly);
    }

    @Override
    public void action(Joueur j) {
        this.monopoly.inter.afficher("vous tombez sur la case \"Allez en Prison\" ...");
        j.allerEnPrison();
    }
}