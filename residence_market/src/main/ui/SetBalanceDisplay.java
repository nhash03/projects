package ui;

import model.Buyer;
import model.Residences;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Display a window that the buyer can set the account balance
public class SetBalanceDisplay extends JFrame {
    JFrame frame;
    JTextField target;
    JButton setBalButton;
    Residences residences;
    Buyer buyer;
    static SetBalHandler handler;

    // EFFECTS : make a frame that can gets and set the target value for account balance
    public SetBalanceDisplay(Buyer buyer, Residences residences) {
        this.buyer = buyer;
        this.residences = residences;
        handler = new SetBalHandler();

        frame = new JFrame("Set Balance");
        frame.setDefaultCloseOperation(3);
        frame.setSize(400,300);
        frame.setVisible(true);

        target = new JTextField("Target Balance");
        target.setBounds(50, 50, 150, 50);
        frame.add(target);
        setBalButton = new JButton("Set!");
        setBalButton.addActionListener(handler);
        setBalButton.setBounds(50, 200, 50, 50);
        frame.setLayout(null);
        frame.add(setBalButton);
    }

    // A class that handles actions for Set balance display
    private class SetBalHandler implements ActionListener {

        // MODIFIES : this
        // EFFECTS : set the balance to given target when the button is pressed
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == setBalButton) {
                String targetBalStr = target.getText();
                buyer.setBalance(Double.parseDouble(targetBalStr));
                frame.setVisible(false);
                frame.dispose();
                new MakeNewBuyerAccountUI(buyer, residences);
            }
        }
    }
}
