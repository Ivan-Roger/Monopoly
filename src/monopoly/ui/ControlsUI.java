/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package monopoly.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 *
 * @author rogeri
 */
class ControlsUI extends JPanel {
    private JTextArea textLog;
    private JLabel[] dice;
    private JButton abort;
    private JButton endTurn;

    public ControlsUI() {
        dice = new JLabel[2];
        initUIComponents();
    }

    private void initUIComponents() {
        this.setBorder(new TitledBorder("Controles"));
        this.setLayout(new GridLayout(1,3));
        
        JPanel logP = new JPanel();
        logP.setLayout(new BorderLayout());
        textLog = new JTextArea();
        logP.add(textLog,BorderLayout.CENTER);
        this.add(logP);
        
        JPanel buttonP = new JPanel();
        buttonP.setLayout(new GridLayout(2,1,10,10));
        abort = new JButton("Abandonner");
        buttonP.add(abort);
        endTurn = new JButton("Terminer le tour");
        buttonP.add(endTurn);
        this.add(buttonP);
        
        JPanel diceP = new JPanel();
        dice[0] = new JLabel("Des 1");
        diceP.add(dice[0]);
        dice[1] = new JLabel("Des 2");
        diceP.add(dice[1]);
        this.add(diceP);
        
    }
    
    public void log(String s) {
        textLog.setText(textLog.getText()+s);
    }
    
    public void setDices(int[] lancer) {
        dice[0].setText(lancer[0]+"/6");
        dice[1].setText(lancer[1]+"/6");
    }
    
}
