package monopoly.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import monopoly.Carreau;
import monopoly.CarreauArgent;
import monopoly.CarreauMouvement;
import monopoly.CarreauPropriete;
import monopoly.CarreauTirage;
import monopoly.Carte;
import monopoly.Compagnie;
import monopoly.Gare;
import monopoly.Groupe;
import monopoly.Joueur;
import monopoly.Monopoly;
import monopoly.ProprieteAConstruire;
import monopoly.ex.ConstruireException;

public class InterfaceTexte extends Interface {

    /* Constructeur */
    public InterfaceTexte(Monopoly monopoly) {
        super(monopoly);
    }

    /* Lecture */
    /**
     * Lecture d'un entier entre les valeurs definies. Saisie verifié.
     *
     * @param min Valeur minimale
     * @param max Valeur maximale
     * @return L'entier saisi
     */
    private int lireInt(int min, int max) {
        Scanner sc = new Scanner(System.in);
        int nb = min - 1;
        while (nb < min || nb > max) {
            System.out.println("Saisir un entier entre " + min + " et " + max + " : ");
            try {
                nb = sc.nextInt();
            } catch (InputMismatchException e) {
                afficher("Veuillez saisir un entier");
                sc = new Scanner(System.in);
            }
        }
        return nb;
    }

    /**
     * Lecture d'un boolean (oui/non).
     *
     * @return (Oui - True / Non - False)
     */
    private boolean lireBoolean() {
        String st = "";
        Scanner sc = new Scanner(System.in);
        while (!"oui".equals(st) && !"non".equals(st)) {
            afficher("Oui / Non ? : ");
            st = sc.nextLine().toLowerCase();
        }
        return "oui".equals(st);
    }

    /**
     * Lecture d'un saisie libre de texte. Non limité en longueur.
     *
     * @return Le texte saisi
     */
    private String lireString() {
        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine();
        return st;
    }

    /* Affichage */
    private void afficher(String message) {
        System.out.println(message);
    }

    @Override
    public void afficherInfosJoueur(Joueur j) {
        afficher("");
        afficher("--- Joueur suivant ---");
        afficher("Nom : " + j.getNom());
        afficher("Position : " + j.getPosition().getNomCarreau() + "(" + j.getPosition().getId() + ")");
        afficher("Cash : " + j.getCash() + "€");
        if (j.getNbLiberation() > 0) {
            afficher("Carte de Liberation : " + j.getNbLiberation());
        }

        afficher("Propriétés :");
        if (j.getProprietes().size() > 0) {
            afficher("  Terrains :");
        }
        for (ProprieteAConstruire p : j.getProprietes()) {
            afficher("    " + p.toString());
        }

        if (j.getGares().size() > 0) {
            afficher("  Gares :");
        }
        for (Gare g : j.getGares()) {
            afficher("    " + g.toString());
        }

        if (j.getCompagnies().size() > 0) {
            afficher("  Compagnies :");
        }
        for (Compagnie c : j.getCompagnies()) {
            afficher("    " + c.toString());
        }

        afficher("");
    }

    @Override
    public void afficherInfosTour(int tour) {
        afficher("---===--- Nouveau tour ---===---");
        afficher("Tour n°" + tour);
        afficher("");
    }

    @Override
    public void afficherLancerDes(int[] lancer) {
        afficher("Lancer de dés :");
        for (int i : lancer) {
            afficher(i + "/6");
        }
    }

    /**
     * Menu de construction de maison ou d'hotel.
     *
     * @param p
     */
    @Override
    public void menuConstruire(ProprieteAConstruire p) {
        afficher("Vous pouvez construire");
        afficher("  1) Ne rien faire");
        afficher("  2) Construire");
        switch (lireInt(1, 2)) {
            case 2:
                try {
                    p.getGroupe().verifConstruire(p.getProprietaire());
                } catch (ConstruireException ex) {
                    afficher("\nAnnulation : "+ex.getMessage()+"\n");
                    menuConstruire(p);
                }
            default:
                break;
        }
    }

    /**
     * Menu pour payer le loyer lors de l'arrivée sur un terrain.
     *
     * @param c Le terrain
     * @param joueur Le payeur
     */
    public void payerLoyer(CarreauPropriete c, Joueur joueur) {
        int loyer = c.calculLoyer();
        afficher("Vous payez " + loyer + "€ a " + c.getProprietaire().getNom());
        c.getProprietaire().recevoirArgent(joueur.payer(loyer));
    }

    /**
     * Menu lors de la visite du proprietaire sur son terrain.
     *
     * @param c Le terrain.
     */
    public void passageMaison(CarreauPropriete c) {
        afficher("Vous êtes chez vous");
    }

    /**
     * Menu lors de l'arrivée sur un carreau argent.
     *
     * @param j Le visiteur.
     * @param montant Le montant du carreau.
     */
    public void passageArgent(Joueur j, int montant) {
        j.recevoirArgent(montant);
        if (montant < 0) {
            afficher("  Vous payez " + (montant * -1) + "€.");
        } else if (montant > 0) {
            afficher("  Vous recevez " + montant + "€.");
        } else {
            afficher("  Vous passez gratuitement.");
        }
    }

    @Override
    public void initInfosJoueurs(ArrayList<Joueur> j) {
    }

    @Override
    public void pause() {
        afficher("Appuyez sur entrée pour continuer");
        lireString();
    }

    @Override
    public void info(String message) {
        afficher(message);
    }

    @Override
    public void afficherPrison(Joueur j) {
        afficher("Vous etes en prison");
    }

    @Override
    public void passageDepart() {
        afficher("Vous passez par la case Départ, recevez 200€.");
    }

    @Override
    public void finTour(Joueur j) {
        afficher("Fin de votre tour ...");
        afficher("Vous finisez avec " + j.getCash() + "€");
        afficher("Appuyez sur entrée pour terminer le tour ou \"Abandonner\"");
        if (lireString().toLowerCase().equals("abandonner")) {
            j.abandonner();
            pause();
        }

    }

    @Override
    public void afficherLiberation(String message) {
        afficher(message);
    }

    @Override
    public ArrayList<Joueur> saisieJoueurs() {
        ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
        afficher("Saisie des joueurs :");
        String s = "";
        String[] anciensNoms;
        while (!(s.equals("quitter")) && joueurs.size() < 6) {
            afficher("Entrez le nom ou \"quitter\" :");
            s = lireString();
            // Empecher la saisie du meme nom
//            while (errSaisie) {
//                inter.afficher("Veuillez entrer un nom libre :");
//                s = inter.lireString();
//            }
            if (!s.equals("quitter")) {
                joueurs.add(new Joueur(s, monopoly));
            }
        }
        return joueurs;
    }

    @Override
    public void choixModeDemo() {
        afficher("Mode demo ?");
        monopoly.setModeDemo(lireBoolean());
    }

    @Override
    public void afficherPosition(Carreau c, Joueur j) {
        afficher(c.toString());
        if (c instanceof CarreauPropriete && ((CarreauPropriete) c).getProprietaire() == null) {
            afficher("Le terrain est disponible");
        }
    }

    @Override
    public void afficherCarte(Carte c, Joueur j) {
        afficher(c.toString());
    }

    @Override
    public void menuAchatPropriete(CarreauPropriete c, Joueur j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[] selectionConstruction(Groupe g, Joueur j) {
        int[] res = new int[g.getNbProprietes()];
        
        afficher("Construction sur le groupe "+g.getCouleur()+g.getCouleur().name()+((char)27)+" :");
        afficher("  Prix d'achat d'une maison : "+g.getPrixAchatMaison()+"€");
        afficher("  Prix d'achat d'un hotel : "+g.getPrixAchatHotel()+"€");
        
        for (int i = 0; i < res.length; i++) {
            int choix = 0;
            boolean hotel = false;
            afficher("Que construire sur le terrain " + g.getProprietes().get(i).getNomCarreau() + " ?");
            afficher("  1) Ne rien construire");
            afficher("  2) Maison");
            afficher("  3) Hotel");
            switch (lireInt(1, 3)) {
                case 2:
                    afficher("Combien de maison (max " + monopoly.getNbMaisonsMax() + ") :");
                    choix = lireInt(1, monopoly.getNbMaisonsMax());
                    break;
                case 3:
                    afficher("Combien d'hotels (max " + monopoly.getNbHotelsMax() + ") :");
                    choix = lireInt(1, monopoly.getNbHotelsMax());
                    hotel = true;
                    break;
                default:
                    break;
            }
            if (choix > 0) {
                afficher("Confirmer la construction de " + choix + " " + (hotel ? "hotels" : "maison") + " sur " + g.getProprietes().get(i).getNomCarreau());
                if (lireString().toLowerCase().equals("non")) {
                    i--;
                } else {
                    if (hotel) {
                        choix += monopoly.getNbMaisonsMax();
                    }
                    res[i]=choix;
                }
            } else {
                afficher("Passage au terrain suivant.");
            }
        }

        return res;
    }

}

/*


 @Override
 public void afficherEtatConstructions(Groupe g) {
 afficher("Constructions déjà présente sur les propriétés " + g.getCouleur() + g.getCouleur().name() + ((char) 27 + "[0m") + ".");
 for (ProprieteAConstruire p : g.getProprietes()) {
 afficher(p.getNomCarreau() + ":");
 afficher("  Nombre de maisons déjà construites : " + p.getNbMaisons());
 afficher("  Nombre d'hôtels déjà construites : " + p.getNbHotels());
 }
 }

 @Override
 public void afficherConstructionsPossibles(int nbMaisonsConstructibles, int nbHotelConstructibles, Groupe g) {
 afficher("Le coût de construction pour les terrains " + g.getCouleur() + g.getCouleur().name() + ((char) 27 + "[0m") + " est de " + g.getPrixAchatMaison() + "€ par maison et " + g.getPrixAchatHotel() + "€ par hôtel.");
 afficher("Vous pouvez construire jusqu'à " + nbMaisonsConstructibles+ " maisons au total à raison de " + monopoly.getNbMaisonsMax() + " maisons maximum par terrain.");
 afficher("Vous pouvez construire jusqu'à " + nbHotelConstructibles + " hôtels au total à raison de " + monopoly.getNbHotelsMax() + " hôtels maximum par terrain.");
 }

 @Override
 public void afficherChoixMaisonsAConstruire(Groupe g) {
 Joueur j = null;
 Scanner sc = new Scanner(System.in);
 afficher("Combien voulez vous construire de maisons au total ?");
 int nbMaisonsAConstruire = sc.nextInt();
 int[] nbMaisonsAConstruireTerrain = new int[g.getNbProprietes()];
 for (int i = 0; i < g.getNbProprietes(); i++) {
 nbMaisonsAConstruireTerrain[i] = 0;
 }
 afficher("Vous allez maintenant placer vos maisons une à une.");
 while (nbMaisonsAConstruire > 0) {
 int compteur = 1;
 afficher("Sur quelle terrain voulez-vous construire une maison ?");
 for (ProprieteAConstruire prop : g.getProprietes()) {
 j = prop.getProprietaire();
 int nbMaisonsRestantesAPlacer = monopoly.getNbMaisonsMax() - (prop.getNbMaisons() + nbMaisonsAConstruireTerrain[compteur - 1]); // Calcul du nombre de maison plaçable.
 if (nbMaisonsRestantesAPlacer == 0) { // Maximum atteint
 afficher(prop.getNomCarreau() + " (Vous ne pouvez plus placer de maison sur ce terrain).");
 } else { // Construction possible sur le terrain de numero de choix compteur
 afficher(compteur + ")" + prop.getNomCarreau() + "(Vous pouvez encore placer " + nbMaisonsRestantesAPlacer + " maison(s) sur ce terrain).");
 compteur++;
 }
 if (compteur == 1) { // Si compteur n'as pas changé alors il n'y a rien a construire.
 nbMaisonsAConstruire = 0;
 }

 }
 nbMaisonsAConstruire--; // Nombre de tour restants - 1
 int choix = lireInt(1, g.getNbProprietes()); // Choix
 nbMaisonsAConstruireTerrain[choix - 1] = nbMaisonsAConstruireTerrain[choix - 1] + 1; // Enregistrement du nombre de maison a construire
 }

 afficherRecapitulatifChoixMaisonsAConstruire(nbMaisonsAConstruireTerrain, g, j);

 }

 @Override
 public void afficherRecapitulatifChoixMaisonsAConstruire(int[] nbMaisonsAConstruire, Groupe g, Joueur j) {
 afficher("Recapitulatif du choix des constructions :");
 int i = 0;
 for (ProprieteAConstruire prop : g.getProprietes()) {
 afficher(prop.getNomCarreau() + ":");
 afficher("Nombre de maisons déjà construites :" + prop.getNbMaisons());
 afficher("Nombre de maisons que vous voulez construire :" + nbMaisonsAConstruire[i]);
 i++;
 }
 int cout = 0;
 for (i = (g.getNbProprietes() - 1); i >= 0; i--) {
 cout += (nbMaisonsAConstruire[i] * g.getPrixAchatMaison());
 }
 //        if (j.getCash()<(j.getCash() - cout)){
 //            throw new FailliteException("Vous n'aurez pas assez d'argent pour continuer"); 
 //        }
        
 afficher("Si vous valider votre choix il vous restera " + (j.getCash() - cout) + "€.");
 afficher("Vous validez votre choix ?");
 if (lireBoolean()) {
 j.payer(cout);

 int w = 0;
 for (int nb : nbMaisonsAConstruire) {
 g.getProprietes().get(w).construireMaison(nb);
 w++;
 }
 }
 }

 /**
 * Menu d'achat d'un terrain, gare ou compagnie.
 *
 * @param c Le terrain
 * @param j l'acheteur
 **/
/*
 public void menuAchatPropriete(CarreauPropriete c, Joueur j) {
 if (c.getPrixAchat() > j.getCash()) {
 afficher("Vous ne possédez pas assez d'argent pour acheter.");
 afficher("Il vous manque " + (c.getPrixAchat() - j.getCash()) + "€");
 } else {
 if (c instanceof Gare) {
 afficher("  1) Acheter cette gare");
 } else if (c instanceof Compagnie) {
 afficher("  1) Acheter cette compagnie");
 } else if (c instanceof ProprieteAConstruire) {
 afficher("  1) Acheter cette proprieté");
 }
 afficher("  2) Ne rien acheter");
 switch (lireInt(1, 2)) {
 case 1:
 c.achatPropriete(j);
 break;
 default:
 break;
 }
 }
 }

 @Override
 public void verifConstruire(ProprieteAConstruire p) throws ConstruireException {
 // Récupération du propriétaire du terrain
 Joueur j = p.getProprietaire();
 // Verification des conditions qui peuvent empecher la construction
 for (ProprieteAConstruire prop : p.getGroupe().getProprietes()) {
 if (prop.getProprietaire() != j) {
 throw new ConstruireException("Vous n'êtes pas propriétaire de tous les terrains de ce groupe. Vous ne pouvez pas construire.");  // Le joueur possède ou non toutes les proprietes du groupe
 }
 if (prop.getNbMaisons() >= monopoly.getNbMaisonsMax() || prop.getNbHotels() >= monopoly.getNbHotelsMax()) {
 throw new ConstruireException("Tous les terrains de ce groupe possèdent déjà le nombre maximal de maisons et d'hôtels. Vous ne pouvez pas construire.");  // Le joueur possède ou non toutes les proprietes du groupe  // Toutes les constructions possible ont déjà été faites ou non
 }
 }
        
 int i = demandeNbMaison(p);
        
 if ((j.getCash() < i*p.getGroupe().getPrixAchatMaison()) || ((p.getNbMaisons() == monopoly.getNbMaisonsMax()))) {
 throw new ConstruireException("Vous n'avez pas assez d'argent pour construire sur les terrains de ce groupe.");  // Le joueur possède assez d'argent ou non pour construire une maison ou un hôtel
 }
 if (monopoly.getNbMaisonsRestantes() == 0) {
 throw new ConstruireException("Il n'y a plus aucunes maisons constructibles sur le plateau. Vous ne pouvez pas construire.");  // Il reste des maisons constructibles sur le plateau ou non
 }
 if (monopoly.getNbHotelsRestants() == 0) {
 throw new ConstruireException("Il n'y a plus aucuns hôtels constructibles sur le plateau. Vous ne pouvez pas construire.");
 }

 afficherEtatConstructions(p.getGroupe());
        
        
        

 // Calcul du nombre de maisons et d'hôtels constructibles en fonction du nombre restant sur la plateau et de l'argent du joueur.
 int compteurCash = j.getCash();
 int maisons = monopoly.getNbMaisonsMax() * p.getGroupe().getNbProprietes() - p.getGroupe().getNbMaisonsGroupe();
 int hotels = monopoly.getNbHotelsMax() * p.getGroupe().getNbProprietes() - p.getGroupe().getNbHotelsGroupe();

 if (monopoly.getNbMaisonsRestantes() < maisons) {
 maisons = monopoly.getNbMaisonsRestantes();
 }

 if (monopoly.getNbHotelsRestants() < hotels) {
 hotels = monopoly.getNbHotelsRestants();
 }
 int maisonsConstructibles = 0, hotelsConstructibles = -1;
 while ((compteurCash > 0) && (maisons > 0 && hotels  > 0)) {
 if (maisons > 0) {
 compteurCash = compteurCash - p.getGroupe().getPrixAchatMaison();
 maisons--;.
 maisonsConstructibles++;
 }
 if (maisons == 0 && hotels > 0) {
 compteurCash = compteurCash - p.getGroupe().getPrixAchatHotel();
 hotels--;
 hotelsConstructibles++;
 }
 }

 afficherConstructionsPossibles(maisonsConstructibles, hotelsConstructibles, p.getGroupe());
 afficherChoixMaisonsAConstruire(p.getGroupe());
 }

 private int demandeNbMaison(ProprieteAConstruire p) {
 afficher("Combien de maisons voulez-vous construire au total ?");
        
 int i = 0;
 if (p.getGroupe().getNbProprietes()==3){
 i= lireInt(0,12);
 }else if(p.getGroupe().getNbProprietes()==2) {
 i= lireInt(0,8);
 }
       
        
        
        
 return i;
 }

 */
