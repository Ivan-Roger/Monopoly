package monopoly;

import java.util.Scanner;

public class Interface {
    
    /* Attributs */
    public Monopoly monopoly;

    /* Constructeur */
    public Interface(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    
    /* Lecture */   
    public int lireInt() {
        Scanner sc = new Scanner(System.in);
        int nb = sc.nextInt();
        return nb;
    }

    public boolean lireBoolean() {
        afficher("Oui / Non ? : ");
        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine();
        return st == "Oui";
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
        afficher("Cash : " + j.getCash());
        
        afficher("Propriétés :");
        afficher("  Terrains :");
        for(ProprieteAConstruire p : j.getProprietes()) {
            afficherPropriete(p);          
        }
        
        afficher("  Gares :");
        for(Gare g : j.getGares()) {            
            afficher("  "+g.getNomCarreau());                        
        }
        
        afficher("  Compagnies :");
        for(Compagnie c : j.getCompagnies()) {            
            afficher("  "+c.getNomCarreau());                        
        }
        
        afficher("");
    }

    public void afficherInfosTour(int tour) {
        afficher("---===--- Nouveau tour ---===---");
        afficher("Tour n°" + tour);
        afficher("");        
    }
    
    public void afficherPropriete(ProprieteAConstruire p) {
        afficher("Nom : " + p.getNomCarreau() + "(" + p.getId() + ") -  " + "Groupe : " + p.getGroupe().getCouleur());
        if (p.getProprietaire() == null) {
            afficher("Coût de la propriété : " + p.getPrixAchat() + "€");
        }
        if (p.getNbMaisons()>0) {
            afficher("  Nombre de maisons: " + p.getNbMaisons());
        }
        if (p.getNbHotels()>0) {
            afficher("  Nombre d'hôtels: " + p.getNbMaisons());
        }
    }

    public void afficherLancerDes(int[] lancer) {
        afficher("Lancer de dés :");
        for (int i : lancer) {
            afficher(i+"/6");
        }
    }

    public void afficherCompagnie(Compagnie c) {
        afficher("Nom : " + c.getNomCarreau() + "(" + c.getId() + ")");
        afficher("Coût de la compagnie : " + c.getPrixAchat());
    }

    public void afficherCarreauArgent(CarreauArgent c) {
        afficher("Nom : " + c.getNomCarreau() + "(" + c.getId() + ")");
        if (c.getMontant()>0) {
            afficher("Vous gagnez " + c.getMontant() + "€");
        } else if (c.getMontant()<0) {
            afficher("Vous perdez " + (1-c.getMontant()) + "€");
        } else {
            afficher("Bonne balade");
        }
    }

    public void afficherGare(Gare g) {
        this.monopoly.inter.afficher("Nom : " + g.getNomCarreau() + "(" + g.getId() + ")");
        this.monopoly.inter.afficher("Coût de la gare : " + g.getPrixAchat());
    }
}
