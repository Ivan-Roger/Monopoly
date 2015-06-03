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

    private final int coutMaison;
    private final int coutHotel;

    public CarteReparation(String description, int id, Monopoly monopoly, int coutMaison, int coutHotel) {
        super(description, id, monopoly);
        this.coutMaison = coutMaison;
        this.coutHotel = coutHotel;
    }


    @Override
    public void action(Joueur j) {
        j.payer(j.getNbHotels() * this.coutHotel + j.getNbMaisons() * this.coutMaison);
    }

}
