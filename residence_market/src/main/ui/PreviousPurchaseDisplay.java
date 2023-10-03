package ui;

import model.Buyer;
import model.Item;
import model.Residences;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Display a frame with items that have previously bought
public class PreviousPurchaseDisplay extends JFrame {
    JTable resItemsTable;
    JFrame frame;
    JScrollPane scrollBar;
    JButton finishButton;
    Buyer buyer;
    Residences residences;
    static ShowItemHandler handler;

    // EFFECTS : make a frame with a table of previous purchases and a button to return
    public PreviousPurchaseDisplay(Buyer buyer, Residences residences) {
        this.buyer = buyer;
        this.residences = residences;

        frame = new JFrame("Previous Shopping");
        frame.setDefaultCloseOperation(3);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());

        handler = new ShowItemHandler();
        String[] colName = {"item ID", "name", "price", "Notes"};
        List<Item> items = buyer.getAlreadyBought();
        String[][] itemsArray = itemToArr(items);
        resItemsTable = new JTable(itemsArray, colName);
        resItemsTable.setCellSelectionEnabled(false);
        //resItemsTable.setBounds(30, 40, 200, 300);

        scrollBar = new JScrollPane(resItemsTable);
        frame.add(scrollBar, BorderLayout.PAGE_START);

        finishButton = new JButton("return");
        finishButton.addActionListener(handler);
        //finishButton.setBounds(50, 350, 100, 50);
        frame.add(finishButton, BorderLayout.PAGE_END);

        System.out.println(items);

        //frame.setLayout(null);
        frame.setVisible(true);
    }

    // EFFECTS : make a 2D array of items
    public static String[][] itemToArr(List<Item> items) {
        String[][] arr = new String[items.size()][4];
        for (int i = 0; i < items.size(); i++) {
            arr[i][0] = items.get(i).getId().toString();
            arr[i][1] = items.get(i).getName();
            arr[i][2] = String.valueOf(items.get(i).getPrice());
            arr[i][3] = items.get(i).getDescription();
        }
        return arr;
    }

    // A class for handling actions of Previous purchases Display
    public class ShowItemHandler implements ActionListener {

        // EFFECTS : return the user to the main buyer menu when the key is pressed
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == finishButton) {
                new MakeNewBuyerAccountUI(buyer, residences);

                frame.setVisible(false);
                frame.dispose();
            }
        }
    }
}
