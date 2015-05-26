package monopoly;

import java.util.Scanner;

public class Interface {

    public Monopoly monopoly;

    public Interface(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    public void afficher(String aMessage) {
        System.out.println(aMessage);
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
        System.out.println("Nom :" + j.getNom());
        System.out.println("Position :" + j.getPosition());
        System.out.println("Cash :" + j.getCash());
    }
}
