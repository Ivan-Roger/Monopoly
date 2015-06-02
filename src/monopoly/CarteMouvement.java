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
public class CarteMouvement extends Carte{
    private final int mouvement;
    private final String type;

    public CarteMouvement(int mouvement, String type, String description, int id, Monopoly monopoly) {
        super(description, id, monopoly);
        this.mouvement = mouvement;
        this.type = type;
    }
    
    @Override
    public void action(Joueur j) {
        if (type == "relatif") {
            j.setPosition(super.monopoly.getCarreau(j.getPosition().getId() + this.mouvement));
        } else {
            j.setPosition(super.monopoly.getCarreau(mouvement));
        }
    }
    
}
