package monopoly;

public abstract class Carreau {

    protected int numero;
    protected String nomCarreau;
    protected Monopoly monopoly;

    public Carreau(int numero, String nomCarreau, Monopoly monopoly) {
        this.numero = numero;
        this.nomCarreau = nomCarreau;
        this.monopoly = monopoly;
    }
    
    public abstract void action(Joueur joueur);

}
