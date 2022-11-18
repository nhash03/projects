package ui;

import model.Buyer;
import model.Residences;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Display the main menu for a buyer
public class MakeNewBuyerAccountUI {
    JButton shoppingBag;
    JButton previousPurchase;
    JButton startShopping;
    JButton setBalance;
    JButton saveBuyer;
    JButton quit;
    JFrame frame;
    Residences residences;
    Buyer buyer;
    static BuyerHandler buyerHandler;

    // EFFECTS : make a frame with different options for a buyer
    public MakeNewBuyerAccountUI(Buyer buyer, Residences residences) {
        this.buyer = buyer;
        this.residences = residences;
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);

        buyerHandler = new BuyerHandler();
        setButtons();

        shoppingBag.addActionListener(buyerHandler);
        previousPurchase.addActionListener(buyerHandler);
        startShopping.addActionListener(buyerHandler);
        setBalance.addActionListener(buyerHandler);
        saveBuyer.addActionListener(buyerHandler);
        quit.addActionListener(buyerHandler);

        frame.add(shoppingBag);
        frame.add(previousPurchase);
        frame.add(startShopping);
        frame.add(setBalance);
        frame.add(saveBuyer);
        frame.add(quit);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    // MODIFIES : this
    // EFFECTS : set the layout and initialize the buttons
    private void setButtons() {
        shoppingBag = new JButton("Visit the Shopping Bag!");
        shoppingBag.setBounds(50, 50, 150, 50);
        previousPurchase = new JButton("Visit the previous purchases");
        previousPurchase.setBounds(50,150, 150, 50);
        startShopping = new JButton("Start Shopping!");
        startShopping.setBounds(50, 250, 150, 50);
        setBalance = new JButton("Set the balance");
        setBalance.setBounds(50, 350, 150, 50);
        saveBuyer = new JButton("Save account");
        saveBuyer.setBounds(50, 450, 150, 50);
        quit = new JButton("Quit");
        quit.setBounds(50, 550, 150, 50);
    }

    // A class that handles actions for buyer main menu display
    public class BuyerHandler implements ActionListener {

        // EFFECTS : lead to different frames based of the pressed button
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == shoppingBag) {
                new ShoppingBagDisplay(buyer, residences);
                frame.setVisible(false);
                frame.dispose();
            } else if (e.getSource() == previousPurchase) {
                new PreviousPurchaseDisplay(buyer, residences);
                frame.setVisible(false);
                frame.dispose();
            } else if (e.getSource() == startShopping) {
                new StartShoppingDisplay(buyer, residences);
                frame.setVisible(false);
                frame.dispose();
            } else if (e.getSource() == setBalance) {
                new SetBalanceDisplay(buyer, residences);
                frame.setVisible(false);
                frame.dispose();
            } else if (e.getSource() == quit) {
                frame.setVisible(false);
                frame.dispose();
            } else if (e.getSource() == saveBuyer) {
                saveResidences();
            }
        }

        // MODIFIES : this
        // EFFECTS : save the whole residences object and account to the json file
        private void saveResidences() {
            try {
                JsonWriter jsonWriter = new JsonWriter("./data/Residences.json");
                jsonWriter.open();
                jsonWriter.write(residences);
                jsonWriter.close();
                System.out.println("Saved Residences to " + "./data/Residences.json");
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + "./data/Residences.json");
            }
        }
    }
}
