package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

// Representing a residence with a specific name and lists of the sellers, buyers
// and items that are available in it.
public class Residence {
    private String name;
    private HashMap<String, String> buyersAccounts;
    private HashMap<String, String> sellersAccounts;
    private List<Buyer> buyers;
    private List<Seller> sellers;
    private List<Item> items;


    // REQUIRES : length of name > 0
    // EFFECTS : make a residence with the given name which initially has no items,
    // sellers or buyers in it.
    public Residence(String name) {
        this.name = name;
        this.buyers = new ArrayList<>();
        this.sellers = new ArrayList<>();
        this.items = new ArrayList<>();
        this.buyersAccounts = new HashMap<>();
        this.sellersAccounts = new HashMap<>();
    }

    // REQUIRES : length of name > 0
    // EFFECTS : make a residence with the given name, buyers and sellers accounts, buyers, sellers
    // and items
    public Residence(String name, HashMap<String, String> ba, HashMap<String, String> sa,
                     List<Buyer> bs, List<Item> is, List<Seller> ss) {
        this.name = name;
        this.sellersAccounts = sa;
        this.buyers = bs;
        this.sellers = ss;
        this.buyersAccounts = ba;
        this.items = is;
    }

    // REQUIRES : user residence should be one of Walter Gage, Exchange, Totem Park or Place Vanier
    // EFFECTS : returns the user residence from the given name
    public static Residence parseToRes(String userResidence, Residences rs) {
        Residence res;
        if (userResidence.equals("Walter Gage")) {
            res = rs.getResidences().get(0);
        } else if (userResidence.equals("Exchange")) {
            res = rs.getResidences().get(1);
        } else if (userResidence.equals("Totem Park")) {
            res = rs.getResidences().get(2);
        } else {
            res = rs.getResidences().get(3);
        }
        return res;
    }

    // EFFECTS : produces true if the residence has no seller, buyer, seller account, buyer account
    // and items
    public Boolean isEmpty() {
        return (this.getItems().size() == 0
                && this.getBuyers().size() == 0
                && this.getSellers().size() == 0
                && this.getBuyersAccounts().size() == 0
                && this.getSellersAccounts().size() == 0);
    }

    public String getName() {
        return this.name;
    }

    public List<Buyer> getBuyers() {
        return this.buyers;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Seller> getSellers() {
        return sellers;
    }

    public HashMap<String, String> getBuyersAccounts() {
        return this.buyersAccounts;
    }

    public HashMap<String, String> getSellersAccounts() {
        return sellersAccounts;
    }

    public void setBuyers(List<Buyer> buyers) {
        this.buyers = buyers;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setBuyersAccounts(HashMap<String, String> buyersAccounts) {
        this.buyersAccounts = buyersAccounts;
    }

    public void setSellersAccounts(HashMap<String, String> sellersAccounts) {
        this.sellersAccounts = sellersAccounts;
    }

    public void setSellers(List<Seller> sellers) {
        this.sellers = sellers;
    }

    // MODIFIES : this
    // EFFECTS : add a new seller to the residence's list and comment if adding was
    // successful or not.
    public void addNewSeller(Seller s) {
        sellers.add(s);
        sellersAccounts.put(s.getUsername(), s.getPassword());
    }

    // MODIFIES : this
    // EFFECTS : add a new buyer to the residence's list and comment if adding was
    // successful or not.
    public void addNewBuyer(Buyer b) {
//        handleAddingObject(b, this.getBuyers(), "buyer");
        buyers.add(b);
        buyersAccounts.put(b.getUsername(), b.getPassword());
    }

    // MODIFIES : this
    // EFFECTS : add a new item to the residence's list
    public void addNewItem(Item i) {
        items.add(i);
    }


    // EFFECTS : make a json object from the residence
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        JSONArray resItems = new JSONArray();
        JSONArray resBuyers = new JSONArray();
        JSONArray resSellers = new JSONArray();
        JSONObject buyerAccount = new JSONObject();
        JSONObject sellerAccount = new JSONObject();
        jsonObject = Residence.init(jsonObject, resItems, resBuyers, resSellers, buyerAccount, sellerAccount);
        for (Item i: items) {
            resItems.put(i.toJson());
        }
        for (Seller s: sellers) {
            resSellers.put(s.toJson());
        }
        for (Buyer b: buyers) {
            resBuyers.put(b.toJson());
        }
        for (String username : buyersAccounts.keySet()) {
            buyerAccount.put(username, buyersAccounts.get(username));
        }
        for (String username : sellersAccounts.keySet()) {
            sellerAccount.put(username, sellersAccounts.get(username));
        }
        return jsonObject;
    }

    private static JSONObject init(JSONObject jsonObject, JSONArray resItems, JSONArray resBuyers,
                             JSONArray resSellers, JSONObject buyerAccount, JSONObject sellerAccount) {
        jsonObject.put("resItems", resItems);
        jsonObject.put("resBuyers", resBuyers);
        jsonObject.put("resSellers", resSellers);
        jsonObject.put("buyerAccounts", buyerAccount);
        jsonObject.put("sellerAccounts", sellerAccount);
        return jsonObject;
    }

}
