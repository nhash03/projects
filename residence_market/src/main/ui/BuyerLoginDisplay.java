package ui;

import model.Buyer;
import model.LoginSystem;
import model.Residence;
import model.Residences;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Display the login page for a buyer
public class BuyerLoginDisplay {
    JTextField username;
    JFrame frame;
    JTextField password;
    JTextField residence;
    JButton login;
    JLabel label;
    static Handler logHandler;
    Residences residences;

    // EFFECTS : make a frame for buyer logging in
    public BuyerLoginDisplay(Residences residences) {
        this.residences = residences;
        logHandler = new Handler();
        frame = new JFrame("Buyer Login Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(null);
        frame.setVisible(true);

        username = new JTextField("username");
        username.setBounds(50, 50, 150, 20);
        password = new JTextField("password");
        password.setBounds(50, 100, 150, 20);
        residence = new JTextField("Residence");
        residence.setBounds(50, 150, 150, 20);

        login = new JButton("Login");
        login.setBounds(50, 200, 50, 50);
        login.addActionListener(logHandler);

        label = new JLabel("Please enter your details!");
        label.setBounds(50,250,200,20);

        frame.add(username);
        frame.add(password);
        frame.add(residence);
        frame.add(login);
        frame.add(label);
    }

    // A class for handling actions of BuyerLogin Display
    private class Handler implements ActionListener {

        // EFFECTS : does the required action when the button is pressed (handle the result of
        // login index)
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == login) {
                String user = username.getText();
                String pass = password.getText();
                String resString = residence.getText();
                Residence res = handleResidence(resString);
                LoginSystem buyerLogin = new LoginSystem(res, user, pass);
                Integer loginIndex = buyerLogin.buyerLogin();
                handleBuyerLoginIndex(loginIndex, buyerLogin.getUserResidence().getBuyers());
            }
        }

        // EFFECTS : find the residence based on the residence string
        private Residence handleResidence(String resString) {
            Residence res = null;
            switch (resString) {
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

        // EFFECTS : return the index represented by the user, pass and res or
        // return negative numbers
        private void handleBuyerLoginIndex(Integer index, List<Buyer> userList) {
            if (index == -1 | index == -2) {
                label.setText("Doesn't match!");
            } else {
                new MakeNewBuyerAccountUI(userList.get(index), residences);
                frame.setVisible(false);
                frame.dispose();
            }
        }
    }
}
