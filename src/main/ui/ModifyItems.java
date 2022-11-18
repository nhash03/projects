package ui;

import model.Item;
import model.Residences;
import model.Seller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static java.lang.Integer.parseInt;
import static ui.PreviousPurchaseDisplay.itemToArr;

// Display a frame to modify and delete items
public class ModifyItems extends JFrame {
    JTable sellerItems;
    JFrame frame = new JFrame("Modify Item");
    JTextField idToModify = new JTextField("item ID");
    JButton modify = new JButton("Modify");
    JButton finish = new JButton("Return");
    JButton delete = new JButton("Delete Item");
    JScrollPane scrollBar;
    Seller seller;
    Residences residences;
    static ModifyItemsHandler handler;

    // EFFECTS : make a frame that can take item id and modify or delete it
    public ModifyItems(Residences residences, Seller seller) {
        this.residences = residences;
        this.seller = seller;
        handler = new ModifyItemsHandler();

        final String[] colName = {"item ID", "name", "price", "Notes"};
        String[][] itemsArray = itemToArr(seller.getItemsToSell());
        sellerItems = new JTable(itemsArray, colName);
        scrollBar = new JScrollPane(sellerItems);
        scrollBar.setPreferredSize(new Dimension(200, 500));

        frame.setDefaultCloseOperation(3);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.add(idToModify, BorderLayout.CENTER);
        frame.add(scrollBar, BorderLayout.PAGE_START);
        frame.add(finish, BorderLayout.PAGE_END);
        frame.add(modify, BorderLayout.LINE_START);
        frame.add(delete, BorderLayout.LINE_END);

        modify.addActionListener(handler);
        delete.addActionListener(handler);
        finish.addActionListener(handler);
        frame.setVisible(true);
    }

    // A class for handling actions of modify item Display
    public class ModifyItemsHandler implements ActionListener {

        // MODIFIES : this
        // EFFECTS : delete or modify the item with given id based on the button pressed
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == modify) {
                Item item = getItemById(idToModify.getText());
                new Modify(item, seller, residences);
                frame.setVisible(false);
                frame.dispose();
            } else if (e.getSource() == finish) {
                new MakeNewSellerAccountUI(residences, seller);
                frame.setVisible(false);
                frame.dispose();
            } else if (e.getSource() == delete) {
                String id = idToModify.getText();
                seller.deleteItem(getItemById(id));
            }

        }

        // EFFECTS : return the item object with given id
        private Item getItemById(String id) {
            for (Item i : seller.getItemsToSell()) {
                if (i.getId() == parseInt(id)) {
                    return i;
                }
            }
            return null;
        }
    }

}

