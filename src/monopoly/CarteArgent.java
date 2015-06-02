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
public class CarteArgent extends Carte{
    private int argent;

    public CarteArgent(int argent, String description, int id, Monopoly monopoly) {
        super(description, id, monopoly);
        this.argent = argent;
    }

    
    @Override
    public void action(Joueur j) {
       if ( argent < 0) {
           j.payer(argent);
       } else {
           j.recevoirArgent(argent);
       }
    }
    
}
