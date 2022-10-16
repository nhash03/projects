package model;

import java.util.*;

// Representing a residence with a specific name and lists of the sellers, buyers
// and items that are available in it.
public class Residence {
    private String name;
    private Set<Object> buyers;
    private Set<Object> sellers;
    private List<Item> items;

    // REQUIRES : length of name > 0
    // EFFECTS : make a residence with the given name which initially has no items,
    // sellers or buyers in it.
    public Residence(String name) {
        this.name = name;
        this.buyers = new HashSet<>();
        this.sellers = new HashSet<>();
        this.items = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public Set<Object> getBuyers() {
        return this.buyers;
    }

    public List<Item> getItems() {
        return items;
    }

    public Set<Object> getSellers() {
        return sellers;
    }

    // MODIFIES : this
    // EFFECTS : add a new seller to the residence's list and comment if adding was
    // successful or not.
    public void addNewSeller(Seller s) {
        handleAddingObject(s, sellers, "seller");
        sellers.add(s);
    }

    // MODIFIES : this
    // EFFECTS : add a new buyer to the residence's list and comment if adding was
    // successful or not.
    public void addNewBuyer(Buyer b) {
        handleAddingObject(b, this.getBuyers(), "buyer");
        buyers.add(b);
    }

    // MODIFIES : this
    // EFFECTS : add a new item to the residence's list
    public void addNewItem(Item i) {
        items.add(i);
    }

    // EFFECTS : return a string to show if the object who wants to be added to the
    // residence's list has already been there or not
    public String handleAddingObject(Object obj, Set<Object> listOfObjects, String type) {
        if (listOfObjects.contains(obj)) {
            return "Such a " + type + " has already existed in the residence";
        } else {
            return "The " + type + " successfully added to the residence's list";
        }
    }
}
