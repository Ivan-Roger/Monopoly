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
public abstract class Carte {
    private String description;
    private int id;
    protected Monopoly monopoly;

    public Carte(String description, int id, Monopoly monopoly) {
        this.description = description.replace("\"", "");
        this.id = id;
        this.monopoly = monopoly;
    }



    
    public abstract void action(Joueur j);
    
    
}
