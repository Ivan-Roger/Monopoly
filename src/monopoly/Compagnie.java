package monopoly;

public class Compagnie extends CarreauPropriete {

    public Compagnie(int numero, String nomCarreau, Monopoly monopoly, int prixAchat) {
        super(numero, nomCarreau, monopoly, prixAchat);
    }

    public void action() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int calculLoyer() {
        if (this.proprietaire.getNbCompagnies() == 2) {
            
            return this.monopoly.calculTotalDes(this.monopoly.jetDeDes()) * 10;
        }
        else {
            return this.monopoly.calculTotalDes(this.monopoly.jetDeDes()) * 4;
        }
            
    }
    
    @Override
    public void achatPropriete(Joueur j) {
        if (this.prixAchat > this.proprietaire.getCash()) {
            this.monopoly.inter.afficher("Vous ne possédez pas assez d'argent pour acheter cette compagnie");
            this.monopoly.inter.afficher("Il vous manque " + (this.prixAchat - j.getCash()) + " euros pour effectuer cette action");
        } else {
            this.monopoly.inter.afficher("Nom : " + this.nomCarreau + "   " + "Groupe : Compagnie ");
            this.monopoly.inter.afficher("Coût de la propriété : " + this.prixAchat);
            this.monopoly.inter.afficher("Voulez-vous acheter cette propriété ?");
            Boolean b = this.monopoly.inter.lireBoolean();
            if (b == true) {
            this.setProprietaire(j);
            j.addPropriete(this);
            j.payer(this.prixAchat);
            }

        }
    }

    @Override
    public void action(Joueur joueur) {
        if (this.proprietaire == null) {
            this.achatPropriete(joueur);
        } else {
            joueur.payer(this.calculLoyer());
            this.proprietaire.recevoirArgent(this.calculLoyer());
        }
    }
}
