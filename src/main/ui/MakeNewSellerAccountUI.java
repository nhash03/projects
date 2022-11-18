package ui;

import model.Buyer;
import model.Residences;
import model.Seller;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Display the main menu for a seller
public class MakeNewSellerAccountUI extends JFrame {
    static JButton addItem;
    JButton modify;
    JButton handleAccount;
    JButton saveSeller;
    JButton quit;
    JFrame frame;
    Residences residences;
    Seller seller;
    static SellerHandler sellerHandler;

    // EFFECTS : make a frame with different options for a seller
    public MakeNewSellerAccountUI(Residences residences, Seller seller) {
        this.seller = seller;
        this.residences = residences;
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);

        sellerHandler = new SellerHandler();
        setButtons();

        addItem.addActionListener(sellerHandler);
        modify.addActionListener(sellerHandler);
        handleAccount.addActionListener(sellerHandler);
        saveSeller.addActionListener(sellerHandler);
        quit.addActionListener(sellerHandler);

        frame.add(addItem);
        frame.add(modify);
        frame.add(handleAccount);
        frame.add(saveSeller);
        frame.add(quit);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    // MODIFIES : this
    // EFFECTS : set the layout and initialize the buttons
    private void setButtons() {
        addItem = new JButton("Add Item to residence!");
        addItem.setBounds(50, 50, 150, 50);
        modify = new JButton("Modify previous items!");
        modify.setBounds(50, 150, 150, 50);
        handleAccount = new JButton("Handle Account!");
        handleAccount.setBounds(50, 250, 150, 50);
        saveSeller = new JButton("Save Account");
        saveSeller.setBounds(50, 350, 150, 50);
        quit = new JButton("Quit");
        quit.setBounds(50, 550, 150, 50);
    }

    // A class that handles actions for buyer main menu display
    public class SellerHandler implements ActionListener {

        // EFFECTS : lead to different frames based of the pressed button
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addItem) {
                new AddItemDisplay(residences, seller);
                frame.setVisible(false);
                frame.dispose();
            } else if (e.getSource() == modify) {
                new ModifyItems(residences, seller);
                frame.setVisible(false);
                frame.dispose();
            } else if (e.getSource() == handleAccount) {
                new HandleAccountDisplay(residences, seller);
                frame.setVisible(false);
                frame.dispose();
            } else if (e.getSource() == saveSeller) {
                saveResidences();
            } else if (e.getSource() == quit) {
                frame.setVisible(false);
                frame.dispose();
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

