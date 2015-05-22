package monopoly;

public class ProprieteAConstruire extends CarreauPropriete {

    private int nbMaisons = 0;
    private int nbHotels = 0;
    private int[] loyers;
    private Groupe groupePropriete;

    public ProprieteAConstruire(int numero, String nomCarreau, Monopoly monopoly, int prixAchat, Groupe g, int[] loyers) {
        super(numero, nomCarreau, monopoly, prixAchat);
        this.loyers = loyers;
        this.groupePropriete = g;
    }

    @Override
    public void achatPropriete(Joueur joueur) {

        if (this.prixAchat > this.proprietaire.getCash()) {
            this.monopoly.inter.afficher("Vous ne possédez pas assez d'argent pour acheter cette propriété");
            this.monopoly.inter.afficher("Il vous manque " + (this.prixAchat - joueur.getCash()) + " euros pour effectuer cette action");
        } else {
            this.monopoly.inter.afficher("Nom : " + this.nomCarreau + "   " + "Groupe : " + this.getGroupe());
            this.monopoly.inter.afficher("Coût de la propriété : " + this.prixAchat);
            this.monopoly.inter.afficher("Voulez-vous acheter cette propriété ?");
            Boolean b = this.monopoly.inter.lireBoolean();
            if (b == true) {
            this.setProprietaire(joueur);
            joueur.addPropriete(this);
            joueur.payer(this.prixAchat);
            }

        }
    }

    @Override
    public int calculLoyer(Joueur j) {
        if (this.nbHotels < 1) {
            return this.loyers[this.nbMaisons];
        } else {
            return this.loyers[5];
        }
        
    }

    public void setNbMaisons(int nb) {
        this.nbMaisons = nb;
    }

    public void setHotel(int hot) {
        this.nbHotels = hot;
    }

    public int getNbMaisons() {
        return this.nbMaisons;
    }

    public int getNbHotels() {
        return this.nbHotels;
    }

    public Groupe getGroupe() {
        return this.groupePropriete;
    }

    @Override
    public void action(Joueur joueur) {
        if (this.proprietaire != null) {
            this.achatPropriete(joueur);
        } else {
            joueur.payer(this.calculLoyer(joueur));
            this.proprietaire.recevoirLoyer(this.calculLoyer(joueur));
        }
    }
}
