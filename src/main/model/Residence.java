package model;

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
    private static final Seller s1 =
            new Seller("seller1", new Residence("Walter Gage"), "123", "abc");
    private static final Seller s2 =
            new Seller("seller2", new Residence("Totem Park"), "456", "def");
    private static final Seller s3 =
            new Seller("seller3", new Residence("Exchange"), "789", "ghi");
    private static final Seller s4 =
            new Seller("seller4", new Residence("Place Vanier"), "101", "jkl");

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

    // MODIFIES : this
    // EFFECTS : Add sample item to the residence market
    public void setDefaultItems() {
        List<Item> items = new ArrayList<>();
        if ("Walter Gage".equals(this.name)) {
            items.add(new Item("milk", 1.55, new Residence("Walter Gage"), s1));
            items.add(new Item("yoghurt", 2.3, new Residence("Walter Gage"), s1));
            items.add(new Item("pencil", 3.61, new Residence("Walter Gage"), s1));
            items.add(new Item("honey", 6.5, new Residence("Walter Gage"), s1));
        } else if ("Totem Park".equals(this.name)) {
            items.add(new Item("banana", 1.55, new Residence("Totem Park"), s2));
            items.add(new Item("apple", 2.3, new Residence("Totem Park"), s2));
            items.add(new Item("pear", 3.61, new Residence("Totem Park"), s2));
            items.add(new Item("TV", 500, new Residence("Totem Park"), s2));
        } else if ("Exchange".equals(this.name)) {
            items.add(new Item("coke", 1.55, new Residence("Exchange"), s3));
            items.add(new Item("sugar", 2.3, new Residence("Exchange"), s3));
            items.add(new Item("honey", 6.5, new Residence("Exchange"), s3));
        } else if ("Place Vanier".equals(this.name)) {
            items.add(new Item("coke", 1.55, new Residence("Place Vanier"), s4));
            items.add(new Item("coffee", 3.61, new Residence("Place Vanier"), s4));
            items.add(new Item("honey", 6.5, new Residence("Place Vanier"), s4));
        }
        this.items = items;
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

    //MODIFIES : this
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

}
