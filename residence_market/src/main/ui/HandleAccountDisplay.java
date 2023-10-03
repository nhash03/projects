package ui;

import model.Item;
import model.Residences;
import model.Seller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static ui.PreviousPurchaseDisplay.itemToArr;

// Shows the main profile information to a seller
public class HandleAccountDisplay extends JFrame {
    JFrame frame = new JFrame("Handle Account");
    Seller seller;
    Residences residences;
    JLabel hello;
    JTable sellerItems;
    JScrollPane scrollBar;
    JButton finish;
    static HandleAccountHandler handler;

    // EFFECTS : make a frame with main information of the account
    public HandleAccountDisplay(Residences residences, Seller seller) {
        this.residences = residences;
        this.seller = seller;
        handler = new HandleAccountHandler();

        frame.setSize(400, 700);
        frame.setDefaultCloseOperation(3);

        hello = new JLabel("Hello " + this.seller.getName() + " !"
                + "How is life at " + this.seller.getLocation().getName() + " ?");

        String[] colName = {"item ID", "name", "price", "Notes"};
        List<Item> items = this.seller.getItemsToSell();
        String[][] itemsArray = itemToArr(items);
        sellerItems = new JTable(itemsArray, colName);
        sellerItems.setCellSelectionEnabled(false);

        scrollBar = new JScrollPane(sellerItems);
        //frame.add(scrollBar);

        finish = new JButton("Return");
        finish.addActionListener(handler);
        frame.setLayout(new BorderLayout());
        frame.add(hello, BorderLayout.PAGE_START);
        frame.add(scrollBar, BorderLayout.CENTER);
        frame.add(finish, BorderLayout.PAGE_END);

        frame.setVisible(true);
    }

    // A class that handles actions for handle account display
    public class HandleAccountHandler implements ActionListener {

        // EFFECTS : returned the user to the seller main menu
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == finish) {
                new MakeNewSellerAccountUI(residences, seller);
                frame.setVisible(false);
                frame.dispose();
            }
        }
    }
}

