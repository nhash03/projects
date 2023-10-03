package ui;

import model.Buyer;
import model.Item;
import model.Residences;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Display the shopping bag of the buyer
public class ShoppingBagDisplay {
    JFrame frame = new JFrame("Shopping Bag");
    JTable shoppingBag;
    JScrollPane scrollBar;
    Buyer buyer;
    Residences residences;
    JLabel totalPrice;
    JTextField removeID;
    JButton removeButton;
    JButton payButton;
    JButton returnButton;
    static ShoppingBagHandler handler;

    // EFFECTS : make a frame with a table of added items and options to pay, delete or return
    public ShoppingBagDisplay(Buyer buyer, Residences residences) {
        this.buyer = buyer;
        this.residences = residences;

        frame.setDefaultCloseOperation(3);
        frame.setSize(400, 650);
        frame.setLayout(new BorderLayout());

        handler = new ShoppingBagHandler();
        String[] colName = {"item ID", "name", "price", "Notes"};
        List<Item> items = buyer.getItemsToBuy();
        String[][] itemsArray = PreviousPurchaseDisplay.itemToArr(items);
        shoppingBag = new JTable(itemsArray, colName);
        shoppingBag.setCellSelectionEnabled(false);

        scrollBar = new JScrollPane(shoppingBag);
        frame.add(scrollBar, BorderLayout.PAGE_START);

        totalPrice = new JLabel("You should pay " + buyer.howMuchToPay());
        frame.add(totalPrice);

        removeID = new JTextField("ID to remove");
        frame.add(removeID, BorderLayout.CENTER);

        setButtons();
        frame.setVisible(true);
    }

    // MODIFIES : this
    // EFFECTS : set and initialize the frame buttons
    private void setButtons() {
        removeButton = new JButton("Remove Item");
        //removeButton.setBounds(50, 450, 150,20);
        removeButton.addActionListener(handler);
        frame.add(removeButton, BorderLayout.WEST);
        payButton = new JButton("Pay!");
        //payButton.setBounds(50, 500, 150,20);
        payButton.addActionListener(handler);
        frame.add(payButton, BorderLayout.EAST);
        returnButton = new JButton("Return!");
        //returnButton.setBounds(50, 550, 150,20);
        returnButton.addActionListener(handler);
        frame.add(returnButton, BorderLayout.SOUTH);
    }

    // A class that handle actions for shopping bag display
    private class ShoppingBagHandler implements ActionListener {

        // MODIFIES : this
        // EFFECTS : remove, buy or return based on the pressed button
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == removeButton) {
                String id = removeID.getText();
                MarketApp.removeByID(buyer, id);
            } else if (e.getSource() == payButton) {
                buyer.payForItems();
            } else if (e.getSource() == returnButton) {
                new MakeNewBuyerAccountUI(buyer, residences);

                frame.setVisible(false);
                frame.dispose();
            }
        }
    }
}
