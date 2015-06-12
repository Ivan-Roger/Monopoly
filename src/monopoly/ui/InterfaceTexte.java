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
                    afficher("Vous avez bien construit.");
                    afficherConstructions(p.getGroupe());
                } catch (ConstruireException ex) {
                    afficher("\nAnnulation : " + ex.getMessage() + "\n");
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
    public int[] selectionConstruction(Groupe g, Joueur j) {
        int[] res = new int[g.getNbProprietes()];

        afficher("Construction sur le groupe " + g.getCouleur() + g.getCouleur().name() + ((char) 27) + "[0m" + " :");
        afficher("  Prix d'achat d'une maison : " + g.getPrixAchatMaison() + "€");
        afficher("  Prix d'achat d'un hotel : " + g.getPrixAchatHotel() + "€");
        afficher("Maisons encore disponibles sur le plateau : "+monopoly.getMaisonsPlateauRestantes());
        afficher("Hotels encore disponibles sur le plateau : "+monopoly.getHotelsPlateauRestants());

        for (int i = 0; i < res.length; i++) {
            int choix = 0;
            boolean hotel = false;
            afficher("Que construire sur le terrain " + g.getProprietes().get(i).getNomCarreau() + " ?");
            afficher("  1) Ne rien construire");
            afficher("  2) Maisons");
            afficher("  3) Hotels");
            switch (lireInt(1, 3)) {
                case 2:
                    afficher("Combien de maisons (max " + monopoly.getMaxMaisonsTerrain() + ") :");
                    choix = lireInt(1, monopoly.getMaxMaisonsTerrain());
                    break;
                case 3:
                    afficher("Combien d'hotels (max " + monopoly.getMaxHotelsTerrain() + ") :");
                    choix = lireInt(1, monopoly.getMaxHotelsTerrain());
                    hotel = true;
                    break;
                default:
                    break;
            }
            if (choix > 0) {
                afficher("Confirmer la construction de " + choix + " " + (hotel ? "hotels" : "maisons") + " sur " + g.getProprietes().get(i).getNomCarreau() + " avec entrée ou \"non\"");
                if (lireString().toLowerCase().equals("non")) {
                    i--;
                } else {
                    if (hotel) {
                        choix += monopoly.getMaxMaisonsTerrain();
                    }
                    res[i] = choix;
                }
            } else {
                afficher("Passage au terrain suivant.");
            }
        }

        return res;
    }

    private void afficherConstructions(Groupe groupe) {
        for (ProprieteAConstruire p : groupe.getProprietes()) {
            afficher("  "+p.getNomCarreau()+" : "+p.getNbMaisons()+" maisons, "+p.getNbHotels()+" hotels.");
        }
    }

}
