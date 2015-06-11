/*
 * Default License :
 * ...
 */

package monopoly.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import monopoly.CarreauArgent;

/**
 * class : CarreauArgentUI
 * by    : rogeri
 * @author rogeri
 */
class CarreauArgentUI extends CarreauUI {
    private CarreauArgent c;
    private JLabel icon;
    private JLabel nom;
    private JTextArea info;

    public CarreauArgentUI(CarreauArgent c, InterfaceGraph inter) {
        super(inter);
        this.c = c;
        initUIComponents();
    }

    @Override
    protected void initUIComponents() {
        this.setBorder(new TitledBorder("Carreau Argent"));
        this.setLayout(new BorderLayout());
        
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        icon = new JLabel("");
        try {
            Image image = ImageIO.read(this.getClass().getResourceAsStream("/assets/CarreauArgent.png"));
            icon.setIcon(new ImageIcon(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        top.add(icon,BorderLayout.WEST);
        JLabel type = new JLabel("Carreau Argent");
        top.add(type,BorderLayout.CENTER);
        nom = new JLabel(c.getNomCarreau());
        top.add(nom,BorderLayout.SOUTH);
        this.add(top, BorderLayout.NORTH);
        
        info = new JTextArea();
        info.setPreferredSize(new Dimension(50,150));
        this.add(info, BorderLayout.CENTER);
    }

}
