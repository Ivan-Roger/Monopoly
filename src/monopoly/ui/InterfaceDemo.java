/*
 * Default License :
 * ...
 */
package monopoly.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import monopoly.Carreau;
import monopoly.Carte;
import monopoly.Joueur;
import monopoly.Monopoly;
import monopoly.ProprieteAConstruire;

/**
 * class : InterfaceDemo by : rogeri
 *
 * @author rogeri
 */
public class InterfaceDemo extends JFrame {

    private Joueur joueur;
    private Monopoly monopoly;
    private JLabel infoDeplacement;
    private JComboBox listeCarreaux;
    private HashMap<String, JComboBox> listeCartes;
    private JTextField cashValue;
    private JComboBox propList;
    private JButton validOpt;
    private JButton validCash;
    private JButton validProp;
    private JButton validTele;
    private JButton validDice;
    private JButton validCard;
    private JComboBox[] listeDes;
    private boolean teleOver = false;
    private boolean diceOver = false;
    private boolean cardOver = false;

    public InterfaceDemo(Monopoly m) {
        super("Demo Monopoly");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.monopoly = m;
        listeDes = new JComboBox[2];
        listeCartes = new HashMap<>();
        this.getContentPane().add(initUIcomponent());
        initListeners();
        this.setSize(new Dimension(600, 700));
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        for (ProprieteAConstruire p : m.getProprietes()) {
            propList.addItem(p);
        }
    }

    private JPanel initUIcomponent() {
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(new JLabel("Demo Monopoly", SwingConstants.CENTER), BorderLayout.NORTH);

        JPanel controles = new JPanel();
        controles.setLayout(new GridLayout(6, 1));

        JPanel cashPanel = new JPanel();
        cashPanel.setLayout(new BoxLayout(cashPanel, BoxLayout.PAGE_AXIS));
        cashValue = new JTextField(10);
        cashPanel.add(cashValue);
        validCash = new JButton("Valider");
        validCash.setEnabled(false);
        cashPanel.add(validCash);
        controles.add(cashPanel);

        JPanel propPanel = new JPanel();
        propPanel.setLayout(new BoxLayout(propPanel, BoxLayout.PAGE_AXIS));
        propList = new JComboBox();
        propPanel.add(propList);
        validProp = new JButton("Valider");
        validProp.setEnabled(false);
        propPanel.add(validProp);
        controles.add(propPanel);

        // Gestion du deplacement
        JPanel telePanel = new JPanel();
        telePanel.setLayout(new BoxLayout(telePanel, BoxLayout.PAGE_AXIS));
        telePanel.add(new JLabel("Téléportation", SwingConstants.CENTER));
        infoDeplacement = new JLabel("");
        telePanel.add(infoDeplacement);
        listeCarreaux = new JComboBox();
        for (Integer i : monopoly.getCarreaux().keySet()) {
            listeCarreaux.addItem(monopoly.getCarreaux().get(i));
        }
        telePanel.add(listeCarreaux);
        validTele = new JButton("Valider");
        validTele.setEnabled(false);
        telePanel.add(validTele);
        controles.add(telePanel);

        // Gestion des dés.
        JPanel dicePanel = new JPanel();
        dicePanel.setLayout(new BoxLayout(dicePanel, BoxLayout.PAGE_AXIS));
        dicePanel.add(new JLabel("Dés", SwingConstants.CENTER));
        listeDes[0] = new JComboBox();
        for (int i = 1; i <= 6; i++) {
            listeDes[0].addItem(i);
        }
        dicePanel.add(listeDes[0]);
        listeDes[1] = new JComboBox();
        for (int i = 1; i <= 6; i++) {
            listeDes[1].addItem(i);
        }
        dicePanel.add(listeDes[1]);
        validDice = new JButton("Valider");
        validDice.setEnabled(false);
        dicePanel.add(validDice);
        controles.add(dicePanel);

        // Gestion des cartes
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.PAGE_AXIS));
        cardPanel.add(new JLabel("Cartes", SwingConstants.CENTER));
        JComboBox cb;
        for (String s : monopoly.getCartes().keySet()) {
            listeCartes.put(s, (cb = new JComboBox()));
            cb.setEnabled(false);
            cardPanel.add(cb);
        }
        validCard = new JButton("Valider");
        validCard.setEnabled(false);
        cardPanel.add(validCard);
        controles.add(cardPanel);

        content.add(controles, BorderLayout.CENTER);

        return content;
    }

    private void initListeners() {
        this.validCash.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                joueur.recevoirArgent(Integer.valueOf(cashValue.getText()) - joueur.getCash());
            }
        });
        this.validProp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ProprieteAConstruire p = (ProprieteAConstruire) propList.getSelectedItem();
                p.setProprietaire(joueur);
                joueur.addPropriete(p);
                propList.removeItem(p);
                if (propList.getItemCount() == 0) {
                    validProp.setEnabled(false);
                }
            }
        });

        this.validTele.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                teleOver = true;
            }

        });
        this.validDice.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                diceOver = true;
            }
        });
        this.validCard.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cardOver = true;
            }
        });
    }

    public void deplacement(Joueur j) {
        propList.removeAllItems();
        for (ProprieteAConstruire x : monopoly.getProprietes()) {
            if (x.getProprietaire() == null) {
                propList.addItem(x);
            }
        }
        validProp.setEnabled(propList.getItemCount() != 0);
        joueur = j;
        cashValue.setText(Integer.toString(j.getCash()));
        validCash.setEnabled(true);
        infoDeplacement.setText("> " + j.getNom() + " <");
        listeCarreaux.setSelectedItem(j.getCarreau());
        validTele.setEnabled(true);
        while (!teleOver) {
            try {
                this.wait();
            } catch (Exception e) {
            }
        }
        teleOver = false;
        validTele.setEnabled(false);
        joueur.setPosition((Carreau) listeCarreaux.getSelectedItem());
    }

    public int[] getDes() {
        validDice.setEnabled(true);
        while (!diceOver) {
            try {
                this.wait();
            } catch (Exception e) {
            }
        }
        diceOver = false;
        validDice.setEnabled(false);
        int[] res = new int[2];
        res[0] = (Integer) listeDes[0].getSelectedItem();
        res[1] = (Integer) listeDes[1].getSelectedItem();
        return res;
    }

    public Carte getCarte(String type) {
        for (String t : monopoly.getCartes().keySet()) {
            listeCartes.get(t).removeAllItems();
            for (Carte c : monopoly.getCartes().get(t)) {
                listeCartes.get(t).addItem(c);
            }
        }

        listeCartes.get(type).setEnabled(true);
        validCard.setEnabled(true);
        while (!cardOver) {
            try {
                this.wait();
            } catch (Exception e) {
            }
        }
        cardOver = false;
        validCard.setEnabled(false);
        listeCartes.get(type).setEnabled(false);

        Carte res = (Carte) listeCartes.get(type).getSelectedItem();
        monopoly.getCartes().get(type).remove(res);
        return res;
    }
}
