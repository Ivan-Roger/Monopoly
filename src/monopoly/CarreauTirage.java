package monopoly;

public class CarreauTirage extends CarreauAction {

    private String type;

    public CarreauTirage(int numero, String nomCarreau, Monopoly monopoly, String type) {
        super(numero, nomCarreau, monopoly);
        this.type = type;
    }

    @Override
    public void action(Joueur j) {
        //communauté  3 - 18 - 34
        //chance      8 - 23 - 37
        super.monopoly.inter.afficher("Vous tirez une carte " + type);
        Carte c = monopoly.getCarteSuivante(type);
        monopoly.inter.afficher("Carte : " + c.getDesc());
        c.action(j);

        if (!(c instanceof CarteLiberePrison)) {
            monopoly.addCarteFin(type, c);
        } else {
            j.addCarteLiberation((CarteLiberePrison) c);
        }

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
