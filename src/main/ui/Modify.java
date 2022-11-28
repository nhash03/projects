package ui;

import model.Item;
import model.Residences;
import model.Seller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Display a frame for seller to edit the mentioned item
public class Modify extends JFrame {
    Residences residences;
    Seller seller;
    Item item;
    JFrame frame = new JFrame("Modify");
    JTextField newName = new JTextField("New Name");
    JTextField newPrice = new JTextField("New Price");
    JTextField newDescription = new JTextField("Change Desc.");
    JButton change = new JButton("Change Item");
    JButton finish = new JButton("return");
    static ModHandler handler;

    // EFFECTS : make a frame to edit the item by seller
    public Modify(Item item, Seller seller, Residences residences) {
        this.residences = residences;
        this.seller = seller;
        this.item = item;

        frame.setSize(300, 600);
        frame.setDefaultCloseOperation(3);

        newName.setBounds(50, 50, 200, 50);
        frame.add(newName);
        newPrice.setBounds(50, 150, 200, 50);
        frame.add(newPrice);
        newDescription.setBounds(50, 250, 200, 50);
        frame.add(newDescription);

        change.setBounds(50, 350, 100, 30);
        frame.add(change);
        change.addActionListener(handler);
        finish.setBounds(50, 400, 100, 30);
        frame.add(finish);
        finish.addActionListener(handler);


        frame.setVisible(true);
    }

    // A class for handling actions of modify Display
    public class ModHandler implements ActionListener {

        // MODIFIES : this
        // EFFECTS : change the item when change pressed and return to the main menu
        // when finished press
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == finish) {
                new MakeNewSellerAccountUI(residences, seller);

                frame.setVisible(false);
                frame.dispose();
            } else if (e.getSource() == change) {
                String newNm = newName.getText();
                Double newPrc = Double.parseDouble(newPrice.getText());
                String newDesc = newDescription.getText();
                item.setDescription(newDesc);
                item.setPrice(newPrc);
                item.setName(newNm);
            }
        }
    }
}
