/*
 * Default License :
 * ...
 */
package monopoly.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import monopoly.Carreau;
import monopoly.CarreauArgent;
import monopoly.CarreauMouvement;
import monopoly.CarreauPropriete;
import monopoly.CarreauTirage;
import monopoly.Carte;
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

    protected Joueur joueur;
    private JFrame fenetre;
    private JPanel tab;
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
        fenetre.setSize(new Dimension(1350, 800));
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
    }

    private JPanel initUIComponents() {
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        tab = new JPanel();
        tab.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 10;
        gbc.ipady = 0;

        infoCarreau = new CarreauArgentUI(monopoly.carreauDepart, this);
        tab.add(infoCarreau, gbc);

        plateau = new PlateauUI(this);
        gbc.gridx++;
        gbc.gridwidth = 2;
        tab.add(plateau, gbc);

        players = new PlayerListUI(this);
        gbc.gridx++;
        gbc.gridx++;
        gbc.gridwidth = 1;
        tab.add(players, gbc);

        content.add(tab, BorderLayout.CENTER);

        controls = new ControlsUI(this);
        content.add(controls, BorderLayout.SOUTH);

        return content;
    }

    private JMenuBar initMenuBar() {
        JMenuBar menu = new JMenuBar();

        return menu;
    }

    private void afficher(String message) {
        controls.log(message + "\n");
    }

    @Override
    public void afficherInfosJoueur(Joueur j) {
        if (joueur != null) {
            plateau.updateJoueur(joueur);
            players.updateJoueur(joueur, false);
        }
        joueur = j;
        updateCarreauInfo(getCarreauUI(j.getPosition(), this));
        controls.clearLog();
        plateau.updateJoueur(j);
        players.updateJoueur(j, true);
    }

    @Override
    public void afficherInfosTour(int tour) {
        controls.clearLog();
        controls.log("=== Nouveau tour ===\n" + "Tour n°" + tour + "\n");
    }

    @Override
    public void afficherLancerDes(int[] lancer) {
        controls.setDices(lancer);
    }

    @Override
    public void afficherEtatConstructions(Groupe g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void menuAchatPropriete(CarreauPropriete c, Joueur j) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void menuConstruire(ProprieteAConstruire p) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initInfosJoueurs(ArrayList<Joueur> j) {
        for (Joueur p : j) {
            plateau.addJoueur(p);
            players.addJoueur(p);
        }
    }

    @Override
    public void pause() {
        controls.prompt();
    }

    private CarreauUI getCarreauUI(Carreau p, InterfaceGraph aThis) {
        if (p instanceof ProprieteAConstruire) {
            return new CarreauTerrainUI((ProprieteAConstruire) p, this);
        } else if (p instanceof Gare) {
            return new CarreauGareUI((Gare) p, this);
        } else if (p instanceof Compagnie) {
            return new CarreauCompagnieUI((Compagnie) p, this);
        } else if (p instanceof CarreauArgent) {
            return new CarreauArgentUI((CarreauArgent) p, this);
        } else if (p instanceof CarreauTirage) {
            return new CarreauTirageUI((CarreauTirage) p, this);
        } else if (p instanceof CarreauMouvement) {
            return new CarreauMouvementUI((CarreauMouvement) p, this);
        } else {
            return null;
        }
    }

    private void updateCarreauInfo(CarreauUI carreauUI) {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 10;
        gbc.ipady = 0;

        tab.remove(infoCarreau);
        infoCarreau = carreauUI;
        tab.add(infoCarreau, gbc);
        fenetre.setSize(new Dimension(fenetre.getSize().width+1,fenetre.getSize().height));
        fenetre.setSize(new Dimension(fenetre.getSize().width-1,fenetre.getSize().height));
    }

    @Override
    public void info(String message) {
        JOptionPane.showMessageDialog(fenetre, message);
    }

    @Override
    public void afficherPrison(Joueur j) {
        afficher("Vous etes en Prison.");
    }

    @Override
    public void passageDepart() {
        afficher("Vous passez par la case départ, recevez 200€");
    }

    @Override
    public void finTour(Joueur j) {
        afficher("Fin de votre tour.");
    }

    @Override
    public void afficherLiberation(String message) {
        afficher(message);
    }

    @Override
    public ArrayList<Joueur> saisieJoueurs() {
        ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
        
        joueurs.add(new Joueur("Alpha",monopoly));
        joueurs.add(new Joueur("Beta",monopoly));
        joueurs.add(new Joueur("Gama",monopoly));
        
        return joueurs;
    }

    @Override
    public void choixModeDemo() {
        monopoly.setModeDemo(JOptionPane.showConfirmDialog(fenetre, "Mode Demo ?", "Selection mode", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==0);
    }

    @Override
    public void payerLoyer(CarreauPropriete c, Joueur joueur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void passageMaison(CarreauPropriete c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void passageArgent(Joueur j, int montant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afficherPosition(Carreau c, Joueur j) {
        updateCarreauInfo(getCarreauUI(j.getPosition(), this));
        plateau.updateJoueur(j);
        players.updateJoueur(j, true);
    }

    @Override
    public void afficherCarte(Carte c, Joueur j) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afficherConstructionsPossibles(int nbMaisonsConstructibles, int nbHotelConstructibles, Groupe g) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afficherChoixMaisonsAConstruire(Groupe g) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void verifConstruire(ProprieteAConstruire p) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afficherRecapitulatifChoixMaisonsAConstruire(int[] nbMaisonsAConstruire, Groupe g, Joueur j) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
