/*
 * Default License :
 * ...
 */

package monopoly.ui;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import monopoly.CarreauTirage;

/**
 * class : CarreauTirageUI
 * by    : rogeri
 * @author rogeri
 */
class CarreauTirageUI extends CarreauUI {
    private CarreauTirage c;

    public CarreauTirageUI(CarreauTirage c, InterfaceGraph inter) {
        super(inter);
        this.c = c;
        initUIComponents();
    }

    @Override
    protected void initUIComponents() {
        this.setBorder(new TitledBorder("Carreau Tirage"));
        this.add(new JLabel("Carreau Tirage"));
    }

}
