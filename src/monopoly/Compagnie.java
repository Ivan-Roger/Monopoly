package monopoly;

public class Compagnie extends CarreauPropriete {

    public Compagnie(int numero, String nomCarreau, Monopoly monopoly, int prixAchat) {
        super(numero, nomCarreau, monopoly, prixAchat);
    }

    
    /**
     * Cette fonction permet de calculer le loyer en fonction du nombre de compagnie 
     * lorsque celui-ci tombe sur une case Compagnie appartenant à un autre joueur
     * @return 
     */
    @Override
    public int calculLoyer() {
        if (this.proprietaire.getNbCompagnies() == 2) {
            int[] var = this.monopoly.jetDeDes();
            monopoly.inter.afficherLancerDes(var);
            return this.monopoly.calculTotalDes(var) * 10;
        } else {
            int[] var = this.monopoly.jetDeDes();
            monopoly.inter.afficherLancerDes(var);
            return this.monopoly.calculTotalDes(var) * 4;
        }

    }

    
    
    @Override
    public void achatPropriete(Joueur j) {
        this.setProprietaire(j);
        j.addCompagnie(this);
        j.payer(this.prixAchat);
        this.monopoly.inter.info("Vous venez d'acheter la compagnie.");
    }
}
