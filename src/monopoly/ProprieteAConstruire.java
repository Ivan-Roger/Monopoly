package monopoly;

public class ProprieteAConstruire extends CarreauPropriete {

    private int nbMaisons = 0;
    private int nbHotels = 0;
    private int[] loyers;
    private Groupe groupePropriete;
    private final int nbMaisonsMax = 3; // A mettre dans Monopoly
    private final int nbHotelsMax = 1;  // A mettre dans Monopoly

    public ProprieteAConstruire(int numero, String nomCarreau, Monopoly monopoly, int prixAchat, Groupe g, int[] loyers) {
        super(numero, nomCarreau, monopoly, prixAchat);
        this.loyers = loyers;
        groupePropriete = g;
    }

    @Override
    public void achatPropriete(Joueur joueur) {
        setProprietaire(joueur);
        joueur.addPropriete(this);
        joueur.payer(prixAchat);
        monopoly.inter.afficher("Vous venez d'acheter le terrain.");
    }

    @Override
    public int calculLoyer() {
        if (nbHotels < 1) {
            return loyers[nbMaisons];
        } else {
            return loyers[nbMaisonsMax+nbHotels];
        }

    }

    public int getNbMaisons() {
        return nbMaisons;
    }

    public int getNbHotels() {
        return nbHotels;
    }

    public Groupe getGroupe() {
        return groupePropriete;
    }

        public void Construire() {
        Joueur p = super.getProprietaire();
        boolean[] peutConstruire = null;
        peutConstruire[2] = false; // Toutes les constructions possible ont déjà été faites ou non
        
        for(ProprieteAConstruire prop : groupePropriete.getProprietes()) {
            if (prop.getProprietaire() != p) {                
                peutConstruire[0] = false;  // Le joueur possède ou non toutes les proprietes du groupe
            }
            if (prop.getNbHotels() < nbHotelsMax) {
                peutConstruire[1] = true;  // Toutes les constructions possible ont déjà été faites ou non
        }
        
        if ( ((nbMaisons < nbMaisonsMax) && (p.getCash() < groupePropriete.getPrixAchatMaison()) ) || ( (nbMaisons == nbMaisonsMax) && (p.getCash() < groupePropriete.getPrixAchatHotel()) ) ) {
            peutConstruire[2] = false;  // le joueur possède assez d'argent ou non pour construire une maison ou un hôtel
        }
        if ()   
            
            
            
        if (!peutConstruire[0]) {
            monopoly.inter.afficher("Vous n'êtes pas propriétaire de tous les terrains de ce groupe. Vous ne pouvez pas construire");
        } else if (!peutConstruire[1]) {
            monopoly.inter.afficher("Tous les terrains de ce groupe possède déjà le nombre maximal de maisons et d'hôtels");
        }
        }
        
    }
}
