/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package monopoly;

/**
 *
 * @author germamax
 */
public class CarteAnniversaire extends Carte{

    public CarteAnniversaire(String description, int id, Monopoly monopoly) {
        super(description, id, monopoly);
    }
       
       
    @Override
    public void action(Joueur j) {
        for (Joueur payeur : super.monopoly.getJoueurs()) {
            if (j != payeur) {
                j.recevoirArgent(payeur.payer(10));
            } 
        }
    }
}
