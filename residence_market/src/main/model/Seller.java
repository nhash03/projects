package model;

import org.json.JSONArray;
import org.json.JSONObject;

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
        res.addNewSeller(this);
    }

    public void addSellerEvent() {
        EventLog.getInstance().logEvent(new Event("The seller, " + this.name + " joined "
                + this.getLocation().getName()));
    }

    public List<Item> getItemsToSell() {
        return itemsToSell;
    }

    public void setItemsToSell(List<Item> itemsToSell) {
        this.itemsToSell = new ArrayList<>(itemsToSell);
    }

    // MODIFIES : this
    // EFFECTS : add an item to the selling list
    public void addItemToSell(Item i) {
        itemsToSell.add(i);
        EventLog.getInstance().logEvent(new Event(i.getName() + " has been added to "
                + this.getLocation().getName() + " by " + this.name));
    }

    // MODIFIES : this
    // EFFECTS : delete an item from selling list if it has been previously there
    public void deleteItem(Item i) {
        if (itemsToSell.contains(i)) {
            itemsToSell.remove(i);
        }
        EventLog.getInstance().logEvent(new Event(i.getName() + " has been deleted from "
                + this.getLocation().getName() + " by " + this.name));
    }

    // EFFECTS : make a json object from the seller
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = super.toJson();
        JSONArray itemsJson = new JSONArray();

        for (Item item : itemsToSell) {
            itemsJson.put(item.getId());
        }

        jsonObject.put("buyer", false);
        jsonObject.put("items", itemsJson);

        return jsonObject;
    }
}
