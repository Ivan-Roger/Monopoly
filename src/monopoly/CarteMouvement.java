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

    public CarteMouvement(int mouvement, String typeMouv, String type, String description, int id, Monopoly monopoly) {
        super(type, description, id, monopoly);
        this.mouvement = mouvement;
        this.typeMouv = typeMouv;
    }

    /**
     * Si le type de mouvement est relatif 
     *      Si le mouvement fait passer le joueur par la case départ alors lui donner 200€ et le positionner
     *      Sinon le positionner
     * Sinon (le mouvement est absolue) 
     *      Si le mouvement le fait avancer et le fait passer par la case départ alors lui donner 200€ et le positionner
     *      Sinon Si le mouvement le fait avancer sans le faire passer par la case départ et le positionner
     *      Sinon faire reculer le joueur
     *
     */
    @Override
    public void action(Joueur j) {
        if ("relatif".equals(typeMouv)) {
            if (j.getPosition().getId() + this.mouvement > monopoly.getTaillePlateau()) {
                j.setPosition(monopoly.getCarreau(j.getPosition().getId() + this.mouvement - monopoly.getTaillePlateau()));
                j.recevoirArgent(200);
            } else {
                j.setPosition(monopoly.getCarreau(j.getPosition().getId() + this.mouvement));
            }
        } else {
            if (mouvement > 0 && j.getPosition().getId() > mouvement) {
                j.setPosition(monopoly.getCarreau(mouvement));
                j.recevoirArgent(200);
            } else if (mouvement > 0 && j.getPosition().getId() < mouvement) {
                j.setPosition(monopoly.getCarreau(mouvement));
            } else {
                mouvement = mouvement * -1;
                j.setPosition(monopoly.getCarreau(mouvement));
            }
        }

    }

}
