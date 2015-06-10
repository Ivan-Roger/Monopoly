/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package monopoly.ui;

import javax.swing.JPanel;
import monopoly.Carreau;

/**
 *
 * @author rogeri
 */
public abstract class CarreauUI extends JPanel {

    public CarreauUI() {
        initUIComponents();
    }
    
    protected abstract void initUIComponents();
}
