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
public class CarteMouvement extends Carte {

    private int mouvement;
    private final String typeMouv;

    public CarteMouvement(int mouvement, String typeMouv, String description, int id, Monopoly monopoly) {
        super(description, id, monopoly);
        this.mouvement = mouvement;
        this.typeMouv = typeMouv;
    }

    @Override
    public void action(Joueur j) {
        if (typeMouv == "relatif") {
            if (j.getPosition().getId() + this.mouvement > 39) {
                j.setPosition(super.monopoly.getCarreau(j.getPosition().getId() + this.mouvement - 39));
                j.recevoirArgent(200);
            } else {
                j.setPosition(super.monopoly.getCarreau(j.getPosition().getId() + this.mouvement));
            }
        } else {
            if (mouvement > 0 && j.getPosition().getId() > mouvement) {
                j.setPosition(super.monopoly.getCarreau(mouvement));
                j.recevoirArgent(200);
            } else if (mouvement > 0 && j.getPosition().getId() < mouvement) {
                j.setPosition(super.monopoly.getCarreau(mouvement));
            } else {
                mouvement = mouvement * -1;
                j.setPosition(super.monopoly.getCarreau(mouvement));
            }
        }

    }

}
