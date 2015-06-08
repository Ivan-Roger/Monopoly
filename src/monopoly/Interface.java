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
        afficher("Carte de Liberation : "+j.getNbLiberation());
        
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
            afficher("Vous gagnez " + c.getMontant() + "€");
        } else if (c.getMontant() < 0) {
            afficher("Vous perdez " + (c.getMontant() * -1) + "€");
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
}
