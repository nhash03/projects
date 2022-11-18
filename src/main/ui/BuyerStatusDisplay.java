package ui;

import model.Residences;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Give two options for registering and logging in for a buyer
public class BuyerStatusDisplay {
    JFrame frame;
    JButton regButton;
    JButton logButton;
    static StatusHandler handler;
    Residences residences;

    // EFFECTS : make a frame with options for registering and logging in
    public BuyerStatusDisplay(Residences residences) {
        this.residences = residences;

        frame = new JFrame("Buyer Status");
        frame.setSize(200, 300);
        frame.setDefaultCloseOperation(3);

        handler = new StatusHandler();

        regButton = new JButton("Register");
        regButton.setBounds(50, 100, 100, 20);
        regButton.addActionListener(handler);
        frame.add(regButton);

        logButton = new JButton("Login");
        logButton.setBounds(50, 200, 100, 20);
        logButton.addActionListener(handler);
        frame.add(logButton);

        frame.setLayout(null);
        frame.setVisible(true);
    }

    // A class that handles actions for buyer status display
    private class StatusHandler implements ActionListener {

        // EFFECTS : gets the buyer to signing or logging in frames when the button is pressed
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == logButton) {
                new BuyerLoginDisplay(residences);

                frame.setVisible(false);
                frame.dispose();
            } else if (e.getSource() == regButton) {
                new BuyerRegisterDisplay(residences);

                frame.setVisible(false);
                frame.dispose();
            }
        }
    }
}
