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
public class CarteReparation extends Carte {

    public CarteReparation(String description, int id, Monopoly monopoly) {
        super(description, id, monopoly);
    }


    
    @Override
    public void action(Joueur j) {
        j.payer(j.getNbHotels()*115 + j.getNbMaisons()*40);
    }
    
    
    }