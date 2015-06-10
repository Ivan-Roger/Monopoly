/*
 * Default License :
 * ...
 */
package monopoly.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;
import monopoly.CarreauArgent;
import monopoly.CarreauPropriete;
import monopoly.CarreauTirage;
import monopoly.Compagnie;
import monopoly.Gare;
import monopoly.Groupe;
import monopoly.Joueur;
import monopoly.Monopoly;
import monopoly.ProprieteAConstruire;

/**
 * class : InterfaceGraph by : rogeri
 *
 * @author rogeri
 */
public class InterfaceGraph extends Interface {

    private JFrame fenetre;
    private CarreauUI infoCarreau;
    private PlateauUI plateau;
    private PlayerListUI players;
    private ControlsUI controls;

    public InterfaceGraph(Monopoly monopoly) {
        super(monopoly);
        fenetre = new JFrame("Monopoly");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setContentPane(initUIComponents());
        fenetre.setJMenuBar(initMenuBar());
        fenetre.setSize(new Dimension(1300, 700));
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
    }

    private JPanel initUIComponents() {
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        JPanel tab = new JPanel();
        tab.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.ipadx=10;
        gbc.ipady=0;
        
        infoCarreau = new CarreauArgentUI(monopoly.carreauDepart);
        tab.add(infoCarreau, gbc);
        
        plateau = new PlateauUI();
        gbc.gridx++;
        gbc.gridwidth=2;
        tab.add(plateau, gbc);
        
        players = new PlayerListUI();
        gbc.gridx++;
        gbc.gridx++;
        gbc.gridwidth=1;
        tab.add(players, gbc);
        
        content.add(tab, BorderLayout.CENTER);
        
        controls = new ControlsUI();
        content.add(controls ,BorderLayout.SOUTH);

        return content;
    }

    private JMenuBar initMenuBar() {
        JMenuBar menu = new JMenuBar();

        return menu;
    }

    @Override
    public int lireInt(int min, int max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean lireBoolean() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String lireString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afficher(String message) {
        controls.log(message+"\n");
    }

    @Override
    public void afficherInfosJoueur(Joueur j) {
        plateau.updateJoueur(j);
        players.updateJoueur(j,true);
    }

    @Override
    public void afficherInfosTour(int tour) {
        controls.log("\n=== Nouveau tour ===\n" + "Tour n°"+tour+"\n");
    }

    @Override
    public void afficherPropriete(ProprieteAConstruire p) {
        infoCarreau = new CarreauTerrainUI(p);
    }

    @Override
    public void afficherPropriete(Compagnie c) {
        infoCarreau = new CarreauCompagnieUI();
    }

    @Override
    public void afficherPropriete(Gare g) {
        infoCarreau = new CarreauGareUI();
    }

    @Override
    public void afficherLancerDes(int[] lancer) {
        controls.setDices(lancer);
    }

    @Override
    public void afficherCarreauArgent(CarreauArgent c) {
        infoCarreau = new CarreauArgentUI(c);
    }

    @Override
    public void afficherCarreauTirage(CarreauTirage c) {
        infoCarreau = new CarreauTirageUI(c);
    }

    @Override
    public void afficherEtatConstructions(Groupe g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void menuAchatPropriete(CarreauPropriete c, Joueur j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void menuConstruire(ProprieteAConstruire p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void menuLoyer(CarreauPropriete c, Joueur joueur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void menuMaison(CarreauPropriete c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void menuGeneral(Joueur j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void menuArgent(Joueur j, int montant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initInfosJoueurs(ArrayList<Joueur> j) {
        for (Joueur p : j) {
            plateau.addJoueur(p);
            players.addJoueur(p);
        }
    }

}
