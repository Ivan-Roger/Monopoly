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
        Scanner sc = new Scanner(System.in);
        Boolean b = sc.nextBoolean();
        return b;
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
        afficher("Nom :" + j.getNom());
        afficher("Position :" + j.getPosition().getNomCarreau());
        afficher("Cash :" + j.getCash());
        
        afficher("Propriétés :");
        afficher("Terrains :");
        for(ProprieteAConstruire p : j.getProprietes()) {
            afficher(p.getGroupe().toString());
            afficher(p.getNomCarreau());
            afficher("Nombre de maisons:" + p.getNbMaisons());
            afficher("Nombre d'hôtels:" + p.getNbMaisons());            
        }
        
        afficher("Gares :");
        for(Gare g : j.getGares()) {            
            afficher(g.getNomCarreau());                        
        }
        
        afficher("Compagnies :");
        for(Compagnie c : j.getCompagnies()) {            
            afficher(c.getNomCarreau());                        
        }
    }

    void afficherInfosTour(Joueur j, int jet) {
        afficher("Nom :" + j.getNom());
        afficher("Total des dès :" + jet);
        afficher("Position :" + j.getPosition());
        
    }

    void afficherLancerDes(int[] lancer) {
        afficher("Lancer de dés :");
        for (int i : lancer) {
            afficher(i+"/6");
        }
    }
}
