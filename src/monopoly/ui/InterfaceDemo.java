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

    private JButton switchMove;
    private int moveMode = 0;
    private JLabel moveLabel;
    private Joueur joueur;
    private Monopoly monopoly;
    private JLabel info;
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
        this.setSize(new Dimension(1000, 700));
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

        JPanel optPanel = new JPanel();
        optPanel.setLayout(new GridLayout(2, 2));
        optPanel.add(new JLabel("Mode Déplacement", SwingConstants.CENTER));
        moveLabel = new JLabel("Selection");
        optPanel.add(moveLabel);
        optPanel.add(new JLabel(""));
        switchMove = new JButton("Changer");
        switchMove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                moveMode = (moveMode == 0 ? 1 : 0);
                moveLabel.setText(moveMode == 0 ? "Selection" : "Lancer de dés");
            }
        });
        optPanel.add(switchMove);
        controles.add(optPanel);

        JPanel cashPanel = new JPanel();
        cashPanel.setLayout(new GridLayout(2, 2));
        cashPanel.add(new JLabel("Cash", SwingConstants.CENTER));
        cashValue = new JTextField(10);
        cashPanel.add(cashValue);
        cashPanel.add(new JLabel(""));
        validCash = new JButton("Valider");
        validCash.setEnabled(false);
        cashPanel.add(validCash);
        controles.add(cashPanel);

        JPanel propPanel = new JPanel();
        propPanel.setLayout(new GridLayout(2, 2));
        propPanel.add(new JLabel("Proprietés", SwingConstants.CENTER));
        propList = new JComboBox();
        propPanel.add(propList);
        propPanel.add(new JLabel(""));
        validProp = new JButton("Valider");
        validProp.setEnabled(false);
        propPanel.add(validProp);
        controles.add(propPanel);

        // Gestion du deplacement
        JPanel telePanel = new JPanel();
        telePanel.setLayout(new GridLayout(2, 2));
        telePanel.add(new JLabel("Téléportation", SwingConstants.CENTER));
        listeCarreaux = new JComboBox();
        for (Integer i : monopoly.getCarreaux().keySet()) {
            listeCarreaux.addItem(monopoly.getCarreaux().get(i));
        }
        telePanel.add(listeCarreaux);
        telePanel.add(new JLabel(""));
        validTele = new JButton("Valider");
        validTele.setEnabled(false);
        telePanel.add(validTele);
        controles.add(telePanel);

        // Gestion des dés.
        JPanel dicePanel = new JPanel();
        dicePanel.setLayout(new GridLayout(2, 2));
        dicePanel.add(new JLabel("Dés", SwingConstants.CENTER));
        JPanel dicePanel2 = new JPanel();
        dicePanel2.setLayout(new GridLayout(2, 1));
        listeDes[0] = new JComboBox();
        for (int i = 1; i <= 6; i++) {
            listeDes[0].addItem(i);
        }
        dicePanel2.add(listeDes[0]);
        listeDes[1] = new JComboBox();
        for (int i = 1; i <= 6; i++) {
            listeDes[1].addItem(i);
        }
        dicePanel2.add(listeDes[1]);
        dicePanel.add(dicePanel2);
        dicePanel.add(new JLabel(""));
        validDice = new JButton("Valider");
        validDice.setEnabled(false);
        dicePanel.add(validDice);
        controles.add(dicePanel);

        // Gestion des cartes
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(2, 2));
        cardPanel.add(new JLabel("Cartes", SwingConstants.CENTER));
        JPanel cardPanel2 = new JPanel();
        cardPanel2.setLayout(new GridLayout(2, 1));
        JComboBox cb;
        for (String s : monopoly.getCartes().keySet()) {
            listeCartes.put(s, (cb = new JComboBox()));
            cb.setEnabled(false);
            cardPanel2.add(cb);
        }
        cardPanel.add(cardPanel2);
        cardPanel.add(new JLabel(""));
        validCard = new JButton("Valider");
        validCard.setEnabled(false);
        cardPanel.add(validCard);
        controles.add(cardPanel);

        content.add(controles, BorderLayout.CENTER);

        info = new JLabel("", SwingConstants.CENTER);
        this.add(info, BorderLayout.SOUTH);

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
                validTele.setEnabled(false);
            }

        });
        this.validDice.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                diceOver = true;
                validDice.setEnabled(false);
            }
        });
        this.validCard.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cardOver = true;
                validCard.setEnabled(false);
            }
        });
    }

    public int deplacement(Joueur j,int[] lancer) {
        lancer[0]=1;
        info.setText("> " + j.getNom() + " <");
        joueur = j;
        int dist;

        propList.removeAllItems();
        for (ProprieteAConstruire x : monopoly.getProprietes()) {
            if (x.getProprietaire() == null) {
                propList.addItem(x);
            }
        }
        validProp.setEnabled(propList.getItemCount() != 0);
        cashValue.setText(Integer.toString(j.getCash()));
        validCash.setEnabled(true);

        if (moveMode == 0) {
            listeCarreaux.setSelectedItem(j.getCarreau());
            validTele.setEnabled(true);
            teleOver = false;
            while (!teleOver) {
                try {
                    this.wait();
                } catch (Exception e) {}
            }
            dist = ((Carreau)listeCarreaux.getSelectedItem()).getId()-j.getPosition().getId();
        } else {
            lancer = getDes();
            dist = lancer[0]+lancer[1];
        }
        return dist;
    }

    public int[] getDes() {
        validDice.setEnabled(true);
        diceOver = false;
        while (!diceOver) {
            try {
                this.wait();
            } catch (Exception e) {
            }
        }
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
        cardOver = false;
        while (!cardOver) {
            try {
                this.wait();
            } catch (Exception e) {
            }
        }
        listeCartes.get(type).setEnabled(false);
        Carte res = (Carte) listeCartes.get(type).getSelectedItem();
        monopoly.getCartes().get(type).remove(res);
        return res;
    }
}
