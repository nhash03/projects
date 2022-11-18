package ui;

import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Display the login page for a seller
public class SellerLoginDisplay {
    JTextField username;
    JFrame frame;
    JTextField password;
    JTextField residence;
    JButton login;
    Residences residences;
    JLabel label;
    static Handler logHandler;

    // EFFECTS : make a frame for seller logging in
    public SellerLoginDisplay(Residences residences) {
        this.residences = residences;
        logHandler = new Handler();
        frame = new JFrame("Seller Login Display");
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

    // A class for handling actions of Seller Login Display
    public class Handler implements ActionListener {

        // EFFECTS : does the required action when the button is pressed (handle the result of
        // login index)
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == login) {
                String user = username.getText();
                String pass = password.getText();
                String resString = residence.getText();
                Residence res = handleResidence(resString);
                LoginSystem sellerLogin = new LoginSystem(res, user, pass);
                Integer loginIndex = sellerLogin.sellerLogin();
                List<Seller> userList = sellerLogin.getUserResidence().getSellers();
                if (loginIndex == -1 | loginIndex == -2) {
                    label.setText("Doesn't match!");
                } else {
                    new MakeNewSellerAccountUI(residences, userList.get(loginIndex));
                    frame.setVisible(false);
                    frame.dispose();
                }
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
    }
}

