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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 *
 * @author rogeri
 */
class ControlsUI extends JPanel {
    private InterfaceGraph inter;
    private JTextArea textLog;
    private JLabel[] dice;
    private JButton abort;
    private JButton endTurn;
    private ImageIcon[] dicesImages;
    private boolean promptOver = false;

    public ControlsUI(InterfaceGraph inter) {
        this.inter=inter;
        dice = new JLabel[2];
        initUIComponents();
        initListeners();
        try {
            BufferedImage dicesFull = ImageIO.read(this.getClass().getResourceAsStream("/assets/dices.png"));
            dicesImages = new ImageIcon[6];
            for (int i = 0; i <6; i++) {
                dicesImages[i] = new ImageIcon(dicesFull.getSubimage(i*50, 0, 50, 50));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initUIComponents() {
        this.setBorder(new TitledBorder("Controles"));
        this.setLayout(new GridLayout(1, 3));

        JPanel logP = new JPanel();
        logP.setLayout(new BorderLayout());
        textLog = new JTextArea();
        textLog.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(textLog);
        scroll.setPreferredSize(new Dimension(100, 200));
        logP.add(scroll, BorderLayout.CENTER);
        this.add(logP);

        JPanel buttonP = new JPanel();
        buttonP.setLayout(new GridLayout(2, 1, 10, 10));
        abort = new JButton("Abandonner");
        abort.setEnabled(false);
        buttonP.add(abort);
        endTurn = new JButton("Terminer le tour");
        endTurn.setEnabled(false);
        buttonP.add(endTurn);
        this.add(buttonP);

        JPanel diceP = new JPanel();
        dice[0] = new JLabel("Des 1");
        diceP.add(dice[0]);
        dice[1] = new JLabel("Des 2");
        diceP.add(dice[1]);
        this.add(diceP);

    }

    private void initListeners() {
        abort.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                promptOver=true;
                inter.joueur.abandonner();
            }
        });
        endTurn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                promptOver=true;
            }
        });
    }

    public void log(String s) {
        textLog.setText(textLog.getText() + s);
    }

    public void setDices(int[] lancer) {
        dice[0].setIcon(dicesImages[lancer[0]-1]);
        dice[1].setIcon(dicesImages[lancer[1]-1]);
    }

    public void clearLog() {
        textLog.setText("");
    }
    
    public void prompt() {
        promptOver = false;
        abort.setEnabled(!promptOver);
        endTurn.setEnabled(!promptOver);
        while (!promptOver) {
            try {
                this.wait();
            } catch (Exception e) {}
        }
        abort.setEnabled(!promptOver);
        endTurn.setEnabled(!promptOver);
    }

}
