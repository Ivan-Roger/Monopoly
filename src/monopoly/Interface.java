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

    public void afficherLancerDes(int[] lancer) {
        afficher("Lancer de dés : ");
        for (int i : lancer) {
            afficher(i + "/6");
        }
    }

    public void afficherInfosTour() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
