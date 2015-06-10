/*
 * Default License :
 * ...
 */

package monopoly.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
    private JTextArea log;
    private JButton getMoney;

    public CarreauArgentUI(CarreauArgent c) {
        super();
        this.c = c;
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
        JLabel type = new JLabel("Carreau Argent ");
        top.add(type,BorderLayout.CENTER);
        nom = new JLabel("");
        top.add(nom,BorderLayout.SOUTH);
        this.add(top, BorderLayout.NORTH);
        
        log = new JTextArea();
        log.setPreferredSize(new Dimension(50,150));
        this.add(log, BorderLayout.CENTER);
        
        getMoney = new JButton("Recuperer l'argent");
        this.add(getMoney,BorderLayout.SOUTH);
    }

}
