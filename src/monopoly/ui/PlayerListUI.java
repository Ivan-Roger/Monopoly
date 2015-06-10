/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.TitledBorder;
import monopoly.Joueur;

/**
 *
 * @author rogeri
 */
class PlayerListUI extends JPanel {

    private JScrollPane list;
    private ArrayList<JPanel> panels;
    private ArrayList<JLabel> icons;
    private ArrayList<JLabel> noms;
    private ArrayList<JLabel> cash;
    private ArrayList<JLabel> statut;
    private ArrayList<JButton> proprietes;
    private ArrayList<JButton> cartes;
    private ArrayList<Joueur> joueurs;

    public PlayerListUI() {
        joueurs = new ArrayList<Joueur>();
        panels = new ArrayList<JPanel>();
        icons = new ArrayList<JLabel>();
        noms = new ArrayList<JLabel>();
        cash = new ArrayList<JLabel>();
        statut = new ArrayList<JLabel>();
        proprietes = new ArrayList<JButton>();
        cartes = new ArrayList<JButton>();
        initUIComponents();
    }

    private void initUIComponents() {
        this.setLayout(new BorderLayout());
        this.setBorder(new TitledBorder("Joueurs"));
        list = new JScrollPane();
        list.setLayout(new ScrollPaneLayout());
        this.add(list, BorderLayout.CENTER);
        this.add(new JLabel("Liste des Joueurs"), BorderLayout.SOUTH);
    }

    public void addJoueur(Joueur j) {
        joueurs.add(j);
        JPanel panel = new JPanel();
        panels.add(panel);
        panel.setBackground(Color.GRAY);
        panel.setLayout(new BorderLayout());
        icons.add(new JLabel(""));
        String imagePath = j.estEnPrison() ? "/assets/AvatarPrison.png" : "/assets/Avatar.png";
        try {
            icons.get(icons.size() - 1).setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream(imagePath))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(icons.get(icons.size() - 1), BorderLayout.WEST);

        JPanel infos = new JPanel();
        infos.setLayout(new GridLayout(3, 2, 10, 10));

        noms.add(new JLabel(j.getNom()));
        infos.add(noms.get(noms.size() - 1));

        proprietes.add(new JButton("Proprietes"));
        infos.add(proprietes.get(proprietes.size() - 1));

        cash.add(new JLabel("Cash : " + j.getCash() + "€"));
        infos.add(cash.get(cash.size() - 1));

        infos.add(new JLabel(""));

        statut.add(new JLabel((j.estEnPrison() ? "En prison" : "...")));
        infos.add(statut.get(statut.size() - 1));

        cartes.add(new JButton("Cartes"));
        infos.add(cartes.get(cartes.size() - 1));
        panel.add(infos, BorderLayout.CENTER);

        list.add(panel);
    }

    public void updateJoueur(Joueur j,boolean current) {
        int index = joueurs.indexOf(j);
        if (!j.abandonne()) {
            if (current) {
                
            }
            cash.get(index).setText("Cash : " + j.getCash() + "€");
            String imagePath = j.estEnPrison() ? "/assets/AvatarPrison.png" : "/assets/Avatar.png";
            try {
                icons.get(index).setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream(imagePath))));
            } catch (IOException e) {
                e.printStackTrace();
            }
            statut.get(index).setText((j.estEnPrison() ? "En prison" : "..."));
        } else {
            proprietes.get(index).setEnabled(false);
            cartes.get(index).setEnabled(false);
            statut.get(index).setText("Abandonne");
        }
    }

}
