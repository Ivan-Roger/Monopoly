package monopoly;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interface {

    /* Attributs */
    public Monopoly monopoly;

    /* Constructeur */
    public Interface(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    /* Lecture */
    public int lireInt(int min, int max) {
        Scanner sc = new Scanner(System.in);
        int nb = min - 1;
        while (nb < min || nb > max) {
            System.out.println("Saisir un entier entre " + min + " et " + max + " : ");
            try {
                nb = sc.nextInt();
            } catch (InputMismatchException e) {
                monopoly.inter.afficher("Veuillez saisir un entier");
                sc = new Scanner(System.in);
            }
        }
        return nb;
    }

    public boolean lireBoolean() {
        String st = "";
        Scanner sc = new Scanner(System.in);
        while (!"oui".equals(st) && !"non".equals(st)) {
            afficher("Oui / Non ? : ");
            st = sc.nextLine().toLowerCase();
        }
        return "oui".equals(st);
    }

    public String lireString() {
        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine();
        return st;
    }

    /* Affichage */
    public void afficher(String message) {
        System.out.println(message);
    }

    public void afficherInfosJoueur(Joueur j) {
        afficher("");
        afficher("--- Joueur suivant ---");
        afficher("Nom : " + j.getNom());
        afficher("Position : " + j.getPosition().getNomCarreau() + "(" + j.getPosition().getId() + ")");
        afficher("Cash : " + j.getCash() + "€");
        afficher("Carte de Liberation : " + j.getNbLiberation());

        afficher("Propriétés :");
        afficher("  Terrains :");
        for (ProprieteAConstruire p : j.getProprietes()) {
            afficherPropriete(p);
        }

        afficher("  Gares :");
        for (Gare g : j.getGares()) {
            afficherGare(g);
        }

        afficher("  Compagnies :");
        for (Compagnie c : j.getCompagnies()) {
            afficherCompagnie(c);
        }

        afficher("");
    }

    public void afficherInfosTour(int tour) {
        afficher("---===--- Nouveau tour ---===---");
        afficher("Tour n°" + tour);
        afficher("");
    }

    public void afficherPropriete(CarreauPropriete c) {
        if (c instanceof Gare) {
            afficherGare((Gare) c);
        } else if (c instanceof Compagnie) {
            afficherCompagnie((Compagnie) c);
        } else if (c instanceof ProprieteAConstruire) {
            afficherPropriete((ProprieteAConstruire) c);
        }

    }

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

    public void afficherLancerDes(int[] lancer) {
        afficher("Lancer de dés :");
        for (int i : lancer) {
            afficher(i + "/6");
        }
    }

    public void afficherCompagnie(Compagnie c) {
        afficher("Nom : " + c.getNomCarreau() + "(" + c.getId() + ")");
        if (c.getProprietaire() == null) {
            afficher("Coût de la compagnie : " + c.getPrixAchat() + "€");
        }
    }

    public void afficherCarreauArgent(CarreauArgent c) {
        afficher("Nom : " + c.getNomCarreau() + "(" + c.getId() + ")");
        if (c.getMontant() > 0) {
            afficher("Vous pouvez recevoir " + c.getMontant() + "€");
        } else if (c.getMontant() < 0) {
            afficher("Vous devez payer " + (c.getMontant() * -1) + "€");
        } else {
            afficher("Bonne balade");
        }
    }

    public void afficherGare(Gare g) {
        afficher("Nom : " + g.getNomCarreau() + "(" + g.getId() + ")");
        if (g.getProprietaire() == null) {
            afficher("Coût de la gare : " + g.getPrixAchat() + "€");
        }
    }

    public void afficherEtatConstructions(Groupe g) {
        afficher("Constructions déjà présente sur les propriétés" + g.getCouleur() + ".");
        for (ProprieteAConstruire p : g.getProprietes()) {
            afficher(p.getNomCarreau() + ":");
            afficher("Nombre de maisons déjà construites :" + p.getNbMaisons());
            afficher("Nombre d'hôtels déjà construites :" + p.getNbHotels());
        }
    }

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

    public void menuLoyer(CarreauPropriete c, Joueur joueur) {
        int loyer = c.calculLoyer();
        afficher("Vous etes chez " + c.getProprietaire());
        afficherPropriete(c);
        afficher("  1) Abandonner");
        afficher("  2) Vous payez " + loyer + "€ a " + c.getProprietaire().getNom());
        switch (lireInt(1, 2)) {
            case 1:
                joueur.abandonner();
                break;
            default:
                c.getProprietaire().recevoirArgent(joueur.payer(loyer));
                break;

        }
    }

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
    
    public void menuGeneral(Joueur j) {
        afficher(" COUCOU C'EST MOI !!!!");
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
}
