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
    private String type;

    public Carte(String type, String description, int id, Monopoly monopoly) {
        this.description = description.replace("\"", "");
        this.id = id;
        this.monopoly = monopoly;
        this.type=type;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }
    
    public String getDesc() {
        return description;
    }
    
    public abstract void action(Joueur j);
    
    @Override
    public String toString() {
        return getType()+" : #"+getId()+" - "+getDesc();
    }
    
}
