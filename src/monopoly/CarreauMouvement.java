package monopoly;

public class CarreauMouvement extends CarreauAction {

    public CarreauMouvement(int numero, String nomCarreau, Monopoly monopoly) {
        super(numero, nomCarreau, monopoly);
    }

    //Menu CarreauMouvement, le joueur peut soit abandonner soit terminer le tour en prison
    @Override
    public void action(Joueur j) {
        this.monopoly.inter.afficher("vous tombez sur la case \"Allez en Prison\" ...");
        j.allerEnPrison();
        this.monopoly.inter.afficher("  1) Abandonner");
        this.monopoly.inter.afficher("  2) Terminer votre tour");
        switch (this.monopoly.inter.lireInt(1, 2)) {
            case 1:
                j.abandonner();
                break;
            default:
                break;
        }
    }
}
