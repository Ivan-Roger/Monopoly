package monopoly.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import monopoly.CarreauArgent;
import monopoly.CarreauMouvement;
import monopoly.CarreauPropriete;
import monopoly.CarreauTirage;
import monopoly.Compagnie;
import monopoly.Gare;
import monopoly.Groupe;
import monopoly.Joueur;
import monopoly.Monopoly;
import monopoly.ProprieteAConstruire;

public class InterfaceTexte extends Interface {

    /* Constructeur */
    public InterfaceTexte(Monopoly monopoly) {
        super(monopoly);
    }

    /* Lecture */
    @Override
    public int lireInt(int min, int max) {
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

    @Override
    public boolean lireBoolean() {
        String st = "";
        Scanner sc = new Scanner(System.in);
        while (!"oui".equals(st) && !"non".equals(st)) {
            afficher("Oui / Non ? : ");
            st = sc.nextLine().toLowerCase();
        }
        return "oui".equals(st);
    }

    @Override
    public String lireString() {
        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine();
        return st;
    }

    /* Affichage */
    @Override
    public void afficher(String message) {
        System.out.println(message);
    }

    @Override
    public void afficherInfosJoueur(Joueur j) {
        afficher("");
        afficher("--- Joueur suivant ---");
        afficher("Nom : " + j.getNom());
        afficher("Position : " + j.getPosition().getNomCarreau() + "(" + j.getPosition().getId() + ")");
        afficher("Cash : " + j.getCash() + "€");
        if (j.getNbLiberation()>0) {afficher("Carte de Liberation : " + j.getNbLiberation());}

        afficher("Propriétés :");
        if (j.getProprietes().size()>0) {afficher("  Terrains :");}
        for (ProprieteAConstruire p : j.getProprietes()) {
            afficherPropriete(p);
        }

        if (j.getGares().size()>0) {afficher("  Gares :");}
        for (Gare g : j.getGares()) {
            afficherPropriete(g);
        }

        if (j.getCompagnies().size()>0) {afficher("  Compagnies :");}
        for (Compagnie c : j.getCompagnies()) {
            afficherPropriete(c);
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
    public void afficherPropriete(ProprieteAConstruire p) {
        afficher("Nom : " + p.getGroupe().getCouleur() + p.getNomCarreau() + ((char) 27 + "[0m") + "(" + p.getId() + ") -  " + "Groupe : " + p.getGroupe().getCouleur().name());
        if (p.getProprietaire() == null) {
            afficher("Coût de la propriété : " + p.getPrixAchat() + "€");
        }
        if (p.getNbMaisons() > 0) {
            afficher("  Nombre de maisons: " + p.getNbMaisons());
        }
        if (p.getNbHotels() > 0) {
            afficher("  Nombre d'hôtels: " + p.getNbMaisons());
        }
    }

    @Override
    public void afficherPropriete(Compagnie c) {
        afficher("Nom : " + c.getNomCarreau() + "(" + c.getId() + ")");
        if (c.getProprietaire() == null) {
            afficher("Coût de la compagnie : " + c.getPrixAchat() + "€");
        }
    }

    @Override
    public void afficherPropriete(Gare g) {
        afficher("Nom : " + g.getNomCarreau() + "(" + g.getId() + ")");
        if (g.getProprietaire() == null) {
            afficher("Coût de la gare : " + g.getPrixAchat() + "€");
        }
    }

    @Override
    public void afficherLancerDes(int[] lancer) {
        afficher("Lancer de dés :");
        for (int i : lancer) {
            afficher(i + "/6");
        }
    }

    @Override
    public void afficherCarreauArgent(CarreauArgent c,Joueur j) {
        afficher("Nom : " + c.getNomCarreau() + "(" + c.getId() + ")");
        if (c.getMontant() > 0) {
            afficher("Vous pouvez recevoir " + c.getMontant() + "€");
        } else if (c.getMontant() < 0) {
            afficher("Vous devez payer " + (c.getMontant() * -1) + "€");
        } else {
            afficher("Bonne balade");
        }
    }

    @Override
    public void afficherCarreauMouvement(CarreauMouvement c, Joueur j) {
        afficher("Vosu tombez sur la case \"Allez en prison.\"");
    }

    @Override
    public void afficherEtatConstructions(Groupe g) {
        afficher("Constructions déjà présente sur les propriétés" + g.getCouleur() + g.getCouleur().name() + ((char) 27 + "[0m") + ".");
        for (ProprieteAConstruire p : g.getProprietes()) {
            afficher(p.getNomCarreau() + ":");
            afficher("Nombre de maisons déjà construites :" + p.getNbMaisons());
            afficher("Nombre d'hôtels déjà construites :" + p.getNbHotels());
        }
    }

    /**
     * Menu d'achat d'un terrain, gare ou compagnie.
     * @param c Le terrain
     * @param j l'acheteur
     */
    public void menuAchatPropriete(CarreauPropriete c, Joueur j) {
        if (c.getPrixAchat() > j.getCash()) {
            afficher("Vous ne possédez pas assez d'argent pour acheter.");
            afficher("Il vous manque " + (c.getPrixAchat() - j.getCash()) + "€");
            afficher("  1) Abandonner");
            afficher("  2) Terminer votre tour");
            switch (lireInt(1, 2)) {
                case 1:
                    j.abandonner();
                    break;
                default:
                    break;
            }
        } else {
            afficherPropriete(c);
            afficher("  1) Abandonner");
            if (c instanceof Gare) {
                afficher("  2) Acheter cette gare");
            } else if (c instanceof Compagnie) {
                afficher("  2) Acheter cette compagnie");
            } else if (c instanceof ProprieteAConstruire) {
                afficher("  2) Acheter cette proprieté");
            }
            afficher("  3) Terminer votre tour");
            switch (lireInt(1, 3)) {
                case 1:
                    j.abandonner();
                    break;
                case 2:
                    c.achatPropriete(j);
                    break;
                default:
                    break;
            }
        }
    }
    
    /**
     * Menu de construction de maison ou d'hotel.
     * @param p 
     */
    public void menuConstruire(ProprieteAConstruire p) {
        
    }

    /**
     * Menu pour payer le loyer lors de l'arrivée sur un terrain.
     * @param c Le terrain
     * @param joueur Le payeur
     */
    public void menuLoyer(CarreauPropriete c, Joueur joueur) {
        int loyer = c.calculLoyer();
        afficher("Vous etes chez " + c.getProprietaire());
        afficherPropriete(c);
        afficher("  1) Abandonner");
        afficher("  2) Payer " + loyer + "€ a " + c.getProprietaire().getNom());
        switch (lireInt(1, 2)) {
            case 1:
                joueur.abandonner();
                break;
            default:
                c.getProprietaire().recevoirArgent(joueur.payer(loyer));
                break;

        }
    }

    /**
     * Menu lors de lavisite du proprietaire sur son terrain.
     * @param c Le terrain.
     */
    public void menuMaison(CarreauPropriete c) {
        afficher("Vous êtes chez vous");
        afficherPropriete(c);
        afficher("  1) Abandonner");
        afficher("  2) Terminer votre tour");
        switch (lireInt(1, 2)) {
            case 1:
                c.getProprietaire().abandonner();
                break;
            default:
                break;
        }
    }
    
    /**
     * Menu general.
     * @param j Le joueur
     */
    public void menuGeneral(Joueur j) {
        afficher("  1) Abandonner");
        afficher("  2) Terminer votre tour");
        switch (lireInt(1, 2)) {
            case 1:
                j.abandonner();
                break;
            default:
                break;
        }
    }

    /**
     * Menu lors de l'arrivée sur un carreau argent.
     * @param j Le visiteur.
     * @param montant Le montant du carreau.
     */
    public void menuArgent(Joueur j, int montant) {
        j.recevoirArgent(montant);
        afficher("  1) Abandonner");
        if (montant<0) {
            afficher("  2) Payer "+(montant*-1)+"€ et terminer votre tour");
        } else if (montant>0) {
            afficher("  2) Recevoir "+montant+"€ et terminer votre tour");
        } else {
            afficher("  2) Terminer votre tour");
        }
        switch (lireInt(1, 2)) {
            case 1:
                j.abandonner();
                break;
            default:
                break;
        }
    }

    @Override
    public void initInfosJoueurs(ArrayList<Joueur> j) {}

    @Override
    public void afficherCarreauTirage(CarreauTirage c) {
        afficher("Vous tirez une carte " + c.getType());
    }

    @Override
    public void pause() {
        lireString();
    }
}
