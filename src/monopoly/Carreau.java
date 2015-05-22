package monopoly;

public abstract class Carreau {

    private int numero;
    private String nomCarreau;
    private Monopoly monopoly;

    public Carreau(int numero, String nomCarreau, Monopoly monopoly) {
        this.numero = numero;
        this.nomCarreau = nomCarreau;
        this.monopoly = monopoly;
    }

}
