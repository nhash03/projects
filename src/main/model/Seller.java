package model;

import java.util.ArrayList;
import java.util.List;

// Representing a seller who lives in a specific residence,with name, personal
// username and password, account balance (in CAD) and a list of available and
// unavailable items that the seller has.
public class Seller extends User {
    private List<Item> itemsToSell;

    // REQUIRES: length of name, username and password > 0 and unique
    //           username and password
    // EFFECTS : balance is set to 0; seller's username and pass
    //           are personal; Initially no history of items that has
    //          been sold.
    public Seller(String nm, Residence res, String un, String pass) {
        super(nm, res, un, pass);
        itemsToSell = new ArrayList<>();
    }

    public List<Item> getItemsToSell() {
        return itemsToSell;
    }

    public void addItemToSell(Item i) {
        itemsToSell.add(i);
    }

    // MODIFIES : this
    // EFFECTS : delete an item from selling list if it has been previously there
    public void deleteItem(Item i) {
        if (itemsToSell.contains(i)) {
            itemsToSell.remove(i);
        }
    }
}
