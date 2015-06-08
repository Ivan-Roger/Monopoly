package monopoly;

public class ProprieteAConstruire extends CarreauPropriete {
    
    /* Attributs */
    private int nbMaisons = 0;
    private int nbHotels = 0;
    private int[] loyers;
    private Groupe groupePropriete;
    private final int nbMaisonsMax = 3; // Modifiables à la construction pour pouvoir créer une partie personalisée
    private final int nbHotelsMax = 1;  // Modifiables à la construction pour pouvoir créer une partie personalisée
    
    
    /* Constructeur */
    public ProprieteAConstruire(int numero, String nomCarreau, Monopoly monopoly, int prixAchat, Groupe g, int[] loyers) {
        super(numero, nomCarreau, monopoly, prixAchat);
        this.loyers = loyers;
        groupePropriete = g;
    }

    /* Réecriture des méthodes super */
    @Override
    public void achatPropriete(Joueur joueur) {
        setProprietaire(joueur);
        joueur.addPropriete(this);
        joueur.payer(prixAchat);
        monopoly.inter.afficher("Vous venez d'acheter le terrain.");
    }

    @Override
    public int calculLoyer() {
        if (nbHotels < nbHotelsMax) {
            return loyers[nbMaisons];
        } else {
            return loyers[nbMaisonsMax+nbHotels];
        }

    }

    /* Getters */
    public int getNbMaisons() {
        return nbMaisons;
    }

    public int getNbHotels() {
        return nbHotels;
    }

    public Groupe getGroupe() {
        return groupePropriete;
    }

    
    /* Autres Méthodes */
    public void Construire() {
        // Récupération du propriétaire du terrain
        Joueur p = super.getProprietaire();
        
        // Initialisation d'un tableau de booléens pour les conditions de construction
        boolean[] peutConstruire = null;
        for(int i = 0; i<5; i++) {
            peutConstruire[i] = true;
        }
        peutConstruire[2] = false; // Toutes les constructions possible ont déjà été faites ou non
        
        
        // Verification des conditions qui peuvent empecher la construction
        for(ProprieteAConstruire prop : groupePropriete.getProprietes()) {
            if (prop.getProprietaire() != p) {                
                peutConstruire[0] = false;  // Le joueur possède ou non toutes les proprietes du groupe
            }
            if (prop.getNbHotels() < nbHotelsMax) {
                peutConstruire[1] = true;  // Toutes les constructions possible ont déjà été faites ou non
        }
        
        if ( ((nbMaisons < nbMaisonsMax) && (p.getCash() < groupePropriete.getPrixAchatMaison()) ) || ( (nbMaisons == nbMaisonsMax) && (p.getCash() < groupePropriete.getPrixAchatHotel()) ) ) {
            peutConstruire[2] = false;  // Le joueur possède assez d'argent ou non pour construire une maison ou un hôtel
        }
        if (monopoly.getNbMaisonsRestantes() == 0) {
            peutConstruire[3] = false;  // Il reste des maisons constructibles sur le plateau ou non
        }   
        if (monopoly.getNbHotelsRestants() == 0) {
            peutConstruire[4] = false;  // Il reste des hôtels constructibles sur le plateau ou non
        }   
            
            
        if (!peutConstruire[0]) {
            monopoly.inter.afficher("Vous n'êtes pas propriétaire de tous les terrains de ce groupe. Vous ne pouvez pas construire.");
        } else if (!peutConstruire[1]) {
            monopoly.inter.afficher("Tous les terrains de ce groupe possèdent déjà le nombre maximal de maisons et d'hôtels. Vous ne pouvez pas construire.");
        } else if (!peutConstruire[2]) {
            monopoly.inter.afficher("Vous n'avez pas assez d'argent pour construire sur les terrains de ce groupe.");
        } else if (!peutConstruire[3]) {
            monopoly.inter.afficher("Il n'y a plus aucunes maisons constructibles sur le plateau. Vous ne pouvez pas construire.");
        } else if (!peutConstruire[4]) {
            monopoly.inter.afficher("Il n'y a plus aucuns hôtels constructibles sur le plateau. Vous ne pouvez pas construire.");
        } else {
            
        }
        
    }
}
