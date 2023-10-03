package ui;

import model.Buyer;
import model.Item;
import model.Residences;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Display a window of available items in residence to the buyer
public class StartShoppingDisplay extends JFrame {
    JTable resItemsTable;
    JScrollPane scrollBar;
    JTextField addItem;
    JButton addButton;
    JButton finish;
    JFrame frame;
    Buyer buyer;
    Residences residences;
    static BazaarHandler handler;

    // EFFECTS : make a frame with a table of available items and a place to add items
    // to the shopping bag
    public StartShoppingDisplay(Buyer buyer, Residences residences) {
        this.buyer = buyer;
        this.residences = residences;

        frame = new JFrame("Bazaar!");
        frame.setDefaultCloseOperation(3);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());

        handler = new BazaarHandler();
        String[] colName = {"item ID", "name", "price", "Notes"};
        List<Item> items = buyer.getLocation().getItems();
        String[][] itemsArray = PreviousPurchaseDisplay.itemToArr(items);
        resItemsTable = new JTable(itemsArray, colName);
        resItemsTable.setCellSelectionEnabled(false);
        //resItemsTable.setBounds(30, 40, 200, 300);

        scrollBar = new JScrollPane(resItemsTable);
        frame.add(scrollBar, BorderLayout.PAGE_START);

        addItem = new JTextField();
        //addItem.setBounds(50, 350, 150, 20);
        frame.add(addItem, BorderLayout.CENTER);

        setButtons();

        //frame.setLayout(null);
        frame.setVisible(true);
    }

    // MODIFIES : this
    // EFFECTS : set and initialize the frame's buttons
    private void setButtons() {
        addButton = new JButton("Add Item");
        //addButton.setBounds(50, 400, 70, 20);
        addButton.addActionListener(handler);
        frame.add(addButton, BorderLayout.WEST);
        finish = new JButton("return");
        //finish.setBounds(50, 450, 70, 20);
        finish.addActionListener(handler);
        frame.add(finish, BorderLayout.EAST);
    }

    // A class that handles the actions of start shopping display
    private class BazaarHandler implements ActionListener {

        // MODIFIES : this
        // EFFECTS : add items to shopping bag or return to the main menu based
        // on the pressed button
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == finish) {
                new MakeNewBuyerAccountUI(buyer, residences);

                frame.setVisible(false);
                frame.dispose();
            } else if (e.getSource() == addButton) {
                String id = addItem.getText();
                MarketApp.addById(buyer, id);
            }
        }
    }
}
