package monopoly;

public class CarreauTirage extends CarreauAction {

    private String type;

    public CarreauTirage(int numero, String nomCarreau, Monopoly monopoly, String type) {
        super(numero, nomCarreau, monopoly);
        this.type = type;
    }

    /**
     * Le joueur tire la carte du type chance ou communauté (selon sa position)
     *
     * Si c'est une carte "libéré de prison" alors le joueur l'ajoute à son inventaire
     * Sinon on remet la carte en bas du paquet
     * 
     * Puis le joueur peut soit abandonner soit terminer le tour
     */
    @Override
    public void action(Joueur j) {
        //communauté  3 - 18 - 34
        //chance      8 - 23 - 37
        monopoly.inter.afficherPosition(this,j);
        Carte c = monopoly.getCarteSuivante(type);
        monopoly.inter.afficherCarte(c,j);
        c.action(j);

        if (!(c instanceof CarteLiberePrison)) {
            monopoly.addCarteFin(type, c);
        } else {
            j.addCarteLiberation((CarteLiberePrison) c);
        }
    }

    public String getType() {
        return type;
    }

}
