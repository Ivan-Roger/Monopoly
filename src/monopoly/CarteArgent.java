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
public class CarteArgent extends Carte {

    private int argent;

    public CarteArgent(String type, String description, int id, Monopoly monopoly, int argent) {
        super(type, description, id, monopoly);
        this.argent = argent;
    }

    /**
     * Si l'attribut argent est négatif alors le joueur doit payer sinon il reçoit l'argent
     * @param j
     **/
    @Override
    public void action(Joueur j) {
        if (argent < 0) {
            j.payer(argent * -1);
        } else {
            j.recevoirArgent(argent);
        }

    }

}
