/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.ui;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import monopoly.Joueur;

/**
 *
 * @author rogeri
 */
class PlateauUI extends JPanel {
    private InterfaceGraph inter;
    private ArrayList<Joueur> joueurs;
    private ArrayList<JLabel> pions;

    public PlateauUI(InterfaceGraph inter) {
        this.inter=inter;
        joueurs = new ArrayList<Joueur>();
        pions = new ArrayList<JLabel>();
        initUIComponents();
        initListeners();
    }

    private void initUIComponents() {
        this.setBorder(new TitledBorder("Plateau"));
        this.setLayout(new OverlayLayout(this));
        JLabel image = new JLabel("", SwingConstants.CENTER);
        try {
            Image plateauFull = ImageIO.read(this.getClass().getResourceAsStream("/assets/plateau.png"));
            Image plateau = plateauFull.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
            image.setIcon(new ImageIcon(plateau));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.add(image);
    }

    private void initListeners() {
        this.setFocusable(true);
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
            //    moveTo(joueur, e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }
    
    private void moveTo(final JLabel j,final int x,final int y) {
        Thread t = new Thread() {
            @Override
            public void run() {
                while (j.getX()!=x || j.getY()!=y) {
                    int movX = 0, movY = 0;
                    try {
                        Thread.sleep(5);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (j.getX()<x) { movX=1;} else if (j.getX()>x) { movX=-1;}
                    if (j.getY()<y) { movY=1;} else if (j.getY()>y) { movY=-1;}
                    j.setBounds(j.getX()+movX, j.getY()+movY, j.getWidth(), j.getHeight());
                }
                this.stop();
            }
        };
        t.start();
    }

    public void addJoueur(Joueur p) {
        joueurs.add(p);
        pions.add(new JLabel(""));
        try {
            pions.get(joueurs.size()-1).setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/assets/Pion.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.add(pions.get(pions.size()-1));
    }
    
    public void updateJoueur(Joueur j) {
        int index = joueurs.indexOf(j);
        /* deplacement en fonction de la position */
    }

}
