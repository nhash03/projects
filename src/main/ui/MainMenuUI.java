package ui;

import model.Residences;
import persistence.JsonReader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Display the main menu for all users
public class MainMenuUI extends JFrame {
    JFrame mainMenuFrame = new JFrame();

    JLabel welcome;
    JButton loadButton;
    JButton buyerLoginButton;
    JButton sellerLoginButton;
    static LoginHandler loginHandler;
    Residences residences = new Residences();

    // MODIFIES : this
    // EFFECTS : create a main menu for users to choose between buyer and seller
    MainMenuUI() {
        setMainFrame();

        loginHandler = new LoginHandler();
        welcome = new JLabel("Welcome to the Market App!");
        welcome.setBounds(100, 100, 300, 20);
        welcome.setForeground(Color.white);
        welcome.setFont(new Font("Serif", Font.BOLD, 20));
        mainMenuFrame.add(welcome);

        sellerLoginButton = new JButton("Seller");
        sellerLoginButton.setBounds(100, 200, 300, 50);
        sellerLoginButton.addActionListener(loginHandler);
        mainMenuFrame.add(sellerLoginButton);

        buyerLoginButton = new JButton("Buyer");
        buyerLoginButton.setBounds(100, 300, 300, 50);
        buyerLoginButton.addActionListener(loginHandler);
        mainMenuFrame.add(buyerLoginButton);

        loadButton = new JButton("Load Data");
        loadButton.setBounds(100, 400, 300, 50);
        loadButton.addActionListener(loginHandler);
        mainMenuFrame.add(loadButton);
        mainMenuFrame.getContentPane().setBackground(Color.BLACK);

        makeLogo();

        mainMenuFrame.setVisible(true);

    }

    // MODIFIES : this
    // EFFECTS : make layout for the main frame
    private void setMainFrame() {
        mainMenuFrame.setSize(500,600);
        mainMenuFrame.setTitle("Main Menu");
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setLayout(null);

        mainMenuFrame.setLocationRelativeTo(null);
    }

    // MODIFIES : this
    // EFFECTS : make layout for the logo
    private void makeLogo() {
        JLabel logo = new JLabel();
        ImageIcon icon = new ImageIcon(new ImageIcon("data/ubc_logo_large.jpg").getImage().getScaledInstance(
                100, 50, Image.SCALE_DEFAULT)
        );
        logo.setVerticalAlignment(JLabel.TOP);
        logo.setIcon(icon);
        logo.setBounds(0,0, 500, 50);
        mainMenuFrame.add(logo);
    }

    // MODIFIES : this
    // EFFECTS : load residences from json file
    private void loadResidences() {
        try {
            JsonReader jsonReader = new JsonReader("./data/Residences.json");
            residences = jsonReader.read();
            System.out.println("Loaded residences from " + "./data/Residences.json");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + "./data/Residences.json");
        }
    }

    // Class for handling actions in main menu
    public class LoginHandler implements ActionListener {

        // EFFECTS : Handle the action of buttons
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buyerLoginButton) {
                new BuyerStatusDisplay(residences);

                mainMenuFrame.setVisible(false);
                mainMenuFrame.dispose();
            } else if (e.getSource() == sellerLoginButton) {
                new SellerStatusDisplay(residences);

                mainMenuFrame.setVisible(false);
                mainMenuFrame.dispose();
            } else if (e.getSource() == loadButton) {
                loadResidences();
            }
        }
    }

    // EFFECTS : run the main menu display
    public static void main(String[] args) throws IOException {
        new MainMenuUI();
    }

}
