/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import monopoly.Carreau;
import monopoly.ProprieteAConstruire;

/**
 *
 * @author rogeri
 */
class CarreauTerrainUI extends CarreauUI {
    private JPanel top;
    private JLabel icon;
    private JLabel nom;
    private JTextArea log;
    private JButton achatMaison;
    private JButton achatHotel;
    private JButton achatTerrain;
    private ProprieteAConstruire c;

    public CarreauTerrainUI(ProprieteAConstruire c, InterfaceGraph inter) {
        super(inter);
        this.c = c;
        initUIComponents();
    }

    @Override
    protected void initUIComponents() {
        this.setBorder(new TitledBorder("Carreau Terrain"));
        this.setLayout(new BorderLayout());
        
        top = new JPanel();
        top.setLayout(new BorderLayout());
        top.setBackground(c.getGroupe().getCouleur().getColor());
        icon = new JLabel("");
        try {
            Image image = ImageIO.read(this.getClass().getResourceAsStream("/assets/CarreauTerrain.png"));
            icon.setIcon(new ImageIcon(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        top.add(icon,BorderLayout.WEST);
        JLabel type = new JLabel("Propriété ");
        top.add(type,BorderLayout.CENTER);
        nom = new JLabel("");
        nom.setText(c.getNomCarreau());
        top.add(nom,BorderLayout.SOUTH);
        this.add(top, BorderLayout.NORTH);
        
        log = new JTextArea();
        log.setPreferredSize(new Dimension(50,150));
        log.setText("Infos sur le Terrain ... a faire !");
        this.add(log, BorderLayout.CENTER);
        
        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(3,1));
        achatMaison = new JButton("Acheter une maison");
        bottom.add(achatMaison);
        achatHotel = new JButton("Acheter un hotel");
        bottom.add(achatHotel);
        achatTerrain = new JButton("Acheter le terrain");
        bottom.add(achatTerrain);
        this.add(bottom, BorderLayout.SOUTH);
    }    
}
