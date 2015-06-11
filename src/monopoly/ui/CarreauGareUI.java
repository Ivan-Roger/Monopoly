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
import monopoly.Gare;

/**
 * class : CarreauGareUI
 * by    : rogeri
 * @author rogeri
 */
class CarreauGareUI extends CarreauUI {
    private Gare g;
    private JLabel icon;
    private JLabel nom;
    private JTextArea info;
    
    public CarreauGareUI(Gare g, InterfaceGraph inter) {
        super(inter);
        this.g=g;
        initUIComponents();
    }

    @Override
    protected void initUIComponents() {
        this.setBorder(new TitledBorder("Gare"));
        this.setLayout(new BorderLayout());
        
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        icon = new JLabel("");
        try {
            Image image = ImageIO.read(this.getClass().getResourceAsStream("/assets/Gare.png"));
            icon.setIcon(new ImageIcon(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        top.add(icon,BorderLayout.WEST);
        JLabel type = new JLabel("Gare");
        top.add(type,BorderLayout.CENTER);
        nom = new JLabel(g.getNomCarreau());
        top.add(nom,BorderLayout.SOUTH);
        this.add(top, BorderLayout.NORTH);
        
        info = new JTextArea();
        info.setPreferredSize(new Dimension(50,150));
        this.add(info, BorderLayout.CENTER);
    }

}
