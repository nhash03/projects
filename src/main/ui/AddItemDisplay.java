package ui;

import model.Item;
import model.Residences;
import model.Seller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Display the adding item frame for the seller
public class AddItemDisplay extends JFrame {
    JFrame frame = new JFrame("Adding Item");
    Seller seller;
    Residences residences;
    JTextField name;
    JTextField price;
    JTextField description;
    JButton add = new JButton("Add Item");
    JButton finish;
    static AddItemHandler handler;

    // EFFECTS : construct the frame for adding item by the seller
    public AddItemDisplay(Residences residences, Seller seller) {
        this.seller = seller;
        this.residences = residences;

        frame.setSize(300, 500);
        frame.setDefaultCloseOperation(3);
        handler = new AddItemHandler();

        name = new JTextField("name");
        name.setBounds(50, 50, 200, 50);
        frame.add(name);
        price = new JTextField("price");
        price.setBounds(50, 150, 200, 50);
        frame.add(price);
        description = new JTextField("description");
        description.setBounds(50, 250, 200, 50);
        frame.add(description);

        add.addActionListener(handler);
        add.setBounds(50, 350, 100, 30);
        frame.add(add);
        finish = new JButton("Return");
        finish.addActionListener(handler);
        finish.setBounds(50, 400, 100, 30);
        frame.add(finish);

        frame.setLayout(null);
        frame.setVisible(true);
    }

    // A class to handle actions of AddItemDisplay
    public class AddItemHandler implements ActionListener {

        // EFFECTS : DOes action when the button is pressed
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == add) {
                String nm = name.getText();
                Double priceDbl = Double.parseDouble(price.getText());
                String desc = description.getText();
                Item item = new Item(nm, priceDbl, seller.getLocation(), seller.getId());
                item.setDescription(desc);
                seller.addItemToSell(item);
            } else if (e.getSource() == finish) {
                new MakeNewSellerAccountUI(residences, seller);

                frame.setVisible(false);
                frame.dispose();
            }
        }
    }
}

