package ui;

import model.Buyer;
import model.Residence;
import model.Residences;
import model.Seller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Display the signing window for a new seller
public class SellerRegisterDisplay {
    JFrame frame;
    JTextField name;
    JTextField username;
    JTextField password;
    JTextField residence;
    JButton regButton;
    Residences residences;
    static RegHandler handler;

    // EFFECTS : show a form with needed information for adding a new seller
    public SellerRegisterDisplay(Residences residences) {
        this.residences = residences;
        handler = new RegHandler();
        frame = new JFrame("Seller Register Display");
        frame.setDefaultCloseOperation(3);
        frame.setSize(300, 400);
        frame.setLayout(null);

        name = new JTextField("name");
        name.setBounds(50, 50, 150, 20);
        username = new JTextField("username");
        username.setBounds(50, 100, 150, 20);
        password = new JTextField("Password");
        password.setBounds(50, 150, 150, 20);
        residence = new JTextField("residence");
        residence.setBounds(50, 200, 150, 20);

        regButton = new JButton("Register");
        regButton.setBounds(50, 250, 150, 50);
        regButton.addActionListener(handler);

        frame.add(name);
        frame.add(username);
        frame.add(password);
        frame.add(residence);
        frame.add(regButton);
        frame.setVisible(true);
    }

    // A class for handling the actions of seller register frame
    private class RegHandler implements ActionListener {

        // EFFECTS : register the seller with given information when the button is pressed
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == regButton) {
                String nm = name.getText();
                String user = username.getText();
                String pass = password.getText();
                String resStr = residence.getText();
                Residence res = handleResidence(resStr);
                Seller seller = new Seller(nm, res, user, pass);
                new MakeNewSellerAccountUI(residences, seller);

                frame.setVisible(false);
                frame.dispose();
            }
        }

        // EFFECTS : find the residence based on the residence string
        private Residence handleResidence(String resStr) {
            Residence res = null;
            switch (resStr) {
                case("Exchange") :
                    res = residences.getResidences().get(1);
                    break;
                case("Walter Gage") :
                    res = residences.getResidences().get(0);
                    break;
                case ("Totem Park") :
                    res = residences.getResidences().get(2);
                    break;
                case ("Place Vanier") :
                    res = residences.getResidences().get(3);
                    break;
                default:
                    System.out.println("\nYour residence is not in our list right now!!");
                    System.out.println("Please register again!");
            }
            return res;
        }
    }
}

