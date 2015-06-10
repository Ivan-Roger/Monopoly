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
import monopoly.Compagnie;

/**
 * class : CarreauCompagnieUI
 * by    : rogeri
 * @author rogeri
 */
class CarreauCompagnieUI extends CarreauUI {
    private Compagnie c;
    private JLabel icon;
    private JLabel nom;
    private JTextArea info;

    public CarreauCompagnieUI(Compagnie c, InterfaceGraph inter) {
        super(inter);
        this.c=c;
        initUIComponents();
    }

    @Override
    protected void initUIComponents() {
        this.setBorder(new TitledBorder("Compagnie"));
        this.setLayout(new BorderLayout());
        
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        icon = new JLabel("");
        try {
            Image image = ImageIO.read(this.getClass().getResourceAsStream("/assets/Compagnie.png"));
            icon.setIcon(new ImageIcon(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        top.add(icon,BorderLayout.WEST);
        JLabel type = new JLabel("Compagnie");
        top.add(type,BorderLayout.CENTER);
        nom = new JLabel("");
        top.add(nom,BorderLayout.SOUTH);
        this.add(top, BorderLayout.NORTH);
        
        info = new JTextArea();
        info.setPreferredSize(new Dimension(50,150));
        this.add(info, BorderLayout.CENTER);
    }

}
