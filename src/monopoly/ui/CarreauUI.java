/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package monopoly.ui;

import java.awt.Dimension;
import javax.swing.JPanel;
import monopoly.Carreau;

/**
 *
 * @author rogeri
 */
public abstract class CarreauUI extends JPanel {
    protected InterfaceGraph inter;
    
    public CarreauUI(InterfaceGraph inter) {
        this.inter=inter;
        this.setPreferredSize(new Dimension(300,400));
    }
    
    protected abstract void initUIComponents();
}
