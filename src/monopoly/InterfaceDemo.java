/*
 * Default License :
 * ...
 */
package monopoly;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * class : InterfaceDemo by : rogeri
 *
 * @author rogeri
 */
public class InterfaceDemo extends JFrame {
    private Joueur joueur;
    private Monopoly monopoly;
    private JLabel nomJoueur;
    private JComboBox listeCarreaux;
    private JComboBox listeCartes;
    private JButton validTele;
    private JButton validDice;
    private JButton validCard;
    private JCheckBoxMenuItem suspend;

    public InterfaceDemo(Monopoly m) {
        super("Demo Monopoly");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.monopoly = m;
        this.setJMenuBar(initMenuBar());
        this.getContentPane().add(initUIcomponent());
        initListeners();
        this.setSize(new Dimension(1200, 700));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JPanel initUIcomponent() {
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(new JLabel("Demo Monopoly", SwingConstants.CENTER), BorderLayout.NORTH);

        JPanel controles = new JPanel();
        controles.setLayout(new GridLayout(3, 1));

        JPanel telePanel = new JPanel();
        telePanel.setLayout(new BoxLayout(telePanel, BoxLayout.PAGE_AXIS));
        telePanel.add(new JLabel("Téléportation", SwingConstants.CENTER));
        nomJoueur = new JLabel("");
        telePanel.add(nomJoueur);
        listeCarreaux = new JComboBox();
        for (Integer i : monopoly.getCarreaux().keySet()) {
            listeCarreaux.addItem(monopoly.getCarreaux().get(i));
        }
        telePanel.add(listeCarreaux);
        validTele = new JButton("Valider");
        telePanel.add(validTele);
        controles.add(telePanel);

        JPanel dicePanel = new JPanel();

        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.PAGE_AXIS));
        cardPanel.add(new JLabel("Cartes", SwingConstants.CENTER));
        listeCartes = new JComboBox();
        for (String s : monopoly.getCartes().keySet()) {
            for (Carte c : monopoly.getCartes().get(s)) {
                listeCartes.addItem(c);
            }
        }
        cardPanel.add(listeCartes);
        validCard = new JButton("Valider");
        cardPanel.add(validCard);
        controles.add(cardPanel);

        content.add(controles, BorderLayout.CENTER);

        return content;
    }

    public void setJoueur(Joueur j) {
        joueur = j;
        nomJoueur.setText(j.getNom());
    }

    private void initListeners() {
        this.validTele.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                joueur.setPosition((Carreau)listeCarreaux.getSelectedItem());
            }
        
        });
        this.suspend.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    
                }
            }
        });
    }

    private JMenuBar initMenuBar() {
        JMenuBar menu = new JMenuBar();
        
        JMenu jeu = new JMenu("Jeu");
        suspend = new JCheckBoxMenuItem();
        jeu.add(suspend);
        
        return menu;
    }

    public void ask(String action) {
        switch (action) {
            case "Deplacement":
                
                break;
        }
    }
}
