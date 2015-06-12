package monopoly;

import java.util.ArrayList;
import monopoly.ex.ConstruireException;

public class Groupe {

    private Monopoly monopoly;
    private int prixAchatMaison;
    private int prixAchatHotel;
    private CouleurPropriete couleur;
    private ArrayList<ProprieteAConstruire> proprietes;

    public Groupe(CouleurPropriete c, Monopoly monopoly) {
        couleur = c;
        proprietes = new ArrayList<ProprieteAConstruire>();
        this.monopoly = monopoly;
    }

    public CouleurPropriete getCouleur() {
        return this.couleur;
    }

    public void addPropriete(ProprieteAConstruire p) {
        proprietes.add(p);
    }

    public ArrayList<ProprieteAConstruire> getProprietes() {
        return proprietes;
    }

    public int getPrixAchatMaison() {
        return prixAchatMaison;
    }

    public int getPrixAchatHotel() {
        return prixAchatHotel;
    }

    public int getNbMaisonsGroupe() {
        int tmp = 0;
        for (ProprieteAConstruire p : proprietes) {
            tmp = tmp + p.getNbMaisons();
        }
        return tmp;
    }

    public int getNbHotelsGroupe() {
        int tmp = 0;
        for (ProprieteAConstruire p : proprietes) {
            tmp = tmp + p.getNbHotels();
        }
        return tmp;
    }

    public int getNbProprietes() {
        int tmp = 0;
        for (ProprieteAConstruire p : proprietes) {
            tmp++;
        }
        return tmp;
    }

    public void setPrixAchatMaison(int prix) {
        this.prixAchatMaison = prix;
    }

    public void setPrixAchatHotel(int prix) {
        this.prixAchatHotel = prix;
    }

    public void verifConstruire(Joueur j) throws ConstruireException {
        int maisonsRestantes = monopoly.getMaisonsPlateauRestantes();
        int hotelsRestants = monopoly.getHotelsPlateauRestants();
        
        for (ProprieteAConstruire p : proprietes) {
            if (p.getProprietaire() != j) {
                throw new ConstruireException("Vous ne possedez pas tous les terrains du groupe. Vous ne pouvez pas construire.");
            }
        }

        int[] choix = monopoly.inter.selectionConstruction(this, j);
        int cash = j.getCash();
        for (int i = 0; i < choix.length; i++) {
            ProprieteAConstruire t = proprietes.get(i);
            if (choix[i] >= monopoly.getMaxMaisonsTerrain() + 1) { // Si tentative de construire des hotels.
                if ((choix[i] - monopoly.getMaxMaisonsTerrain()) > hotelsRestants) { // Si construction de plus d'hotel que le max plateau
                    throw new ConstruireException("Vous ne pouvez plus construire que " + monopoly.getHotelsPlateauRestants() + " hotels sur le plateau");
                }
                if (t.getNbMaisons() < monopoly.getMaxMaisonsTerrain()) { // Si nombre de maisons inferieur au max.
                    throw new ConstruireException("Vous ne pouvez pas construire un hotel sur le terrain " + t.getNomCarreau() + " car vous n'avez pas construit toutes les maisons.");
                }
                if (t.getNbHotels() + (choix[i] - monopoly.getMaxMaisonsTerrain()) > monopoly.getMaxHotelsTerrain()) { // Construire plus d'hotels que le max.
                    throw new ConstruireException("Vous ne pouvez pas construire plus de " + monopoly.getMaxHotelsTerrain() + " hotels sur un terrain.");
                }
                hotelsRestants -= (choix[i] - monopoly.getMaxMaisonsTerrain());
                cash -= (choix[i] - monopoly.getMaxMaisonsTerrain())*this.prixAchatHotel;
            } else {
                if (choix[i] > maisonsRestantes) { // Plus de maisons que maxPlateau
                    throw new ConstruireException("Vous ne pouvez plus contruire que " + monopoly.getMaisonsPlateauRestantes() + " maisons sur le plateau.");
                }
                if (t.getNbMaisons() + choix[i] > monopoly.getMaxMaisonsTerrain()) { // Si tentative de construire plus de maison que le max terrain.
                    throw new ConstruireException("Vous ne pouvez pas construire plus de " + monopoly.getMaxMaisonsTerrain() + " maisons sur un terrain.");
                }
                maisonsRestantes -= choix[i];
                cash -= choix[i]*this.prixAchatMaison;
            }
        }
        if (cash<=0) {
            throw new ConstruireException("Vous n'avez pas assez d'argent pour effectuer cela.");
        }
        
        
        int min = choix[0];
        int max = choix[0];
        for (int i=0; i<choix.length; i++) {
            max = (choix[i]>max ? choix[i] : max ); // Si nb[i] < max alors max = nb[i] sinon max = max
            min = (choix[i]<min ? choix[i] : min ); // Pareil
        }
        if (max-min>1) {
            throw new ConstruireException("Vous devez assurer que vos terrain soient equitablement repartis.");
        }

        for (int i=0; i<choix.length; i++) {
            proprietes.get(i).construire(choix[i]); // Construction
        }
        j.payer(j.getCash()-cash); // Paiement
        /*
         Il faut :
         tout les proprietés du groupe
         ne pas depasser le nbMax de maisons sur le plateau
         ne pas depasser le nbMax de maisons sur le Carreau
         il faut avoir assez d'argent
        
         Valeur de retour :
         tableau d'entier par terrain dans l'ordre du plateau.
         exemple : [2,2,3] / pour l'hotel = nbMaxMaison + 1
        
         */
    }
}
