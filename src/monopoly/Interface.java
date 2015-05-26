package monopoly;

import java.util.Scanner;

public class Interface {

    public Monopoly monopoly;

    public Interface(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    public void afficher(String message) {
        System.out.println(message);
    }

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
    
    public void afficherInfosJoueur(Joueur j) {
        afficher("Nom :" + j.getNom());
        afficher("Position :" + j.getPosition());
        afficher("Cash :" + j.getCash());
    }

    void afficherInfosTour() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void afficherLancerDes(int[] lancer) {
        afficher("Lancer de dés :");
        for (int i : lancer) {
            afficher(i+"/6");
        }
    }
}
