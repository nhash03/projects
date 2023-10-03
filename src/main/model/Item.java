package model;

import org.json.JSONObject;

import java.util.Random;

import static java.lang.Integer.parseInt;

// Representing an item of a specific seller in a certain location with its unique ID.
// The item has its own description, name and price (in CAD) and also represents its seller
// and buyer(if available).
public class Item {
    private String name;
    private String description;
    private double price;
    private Residence location;
    private Integer id;
    private Long sellerID;
    private Buyer buyer;
    private Boolean availability;

    // REQUIRES : length of name >0 and price >= 0
    // EFFECTS : create an item with its own name, price and seller;
    // initially available to buy and no description provided. At first,
    // it does not have any buyer and is located in a specific residence.
    // The id of the item is unique for itself.
    public Item(String name, double price, Residence location, Long sellerID) {
        this.name = name;
        this.description = null;
        this.price = price;
        this.location = location;
        location.addNewItem(this);
        this.id = setID();
        this.sellerID = sellerID;
        this.buyer = null;
        this.availability = true;
    }

    public Item(String name, double price, Residence location, Long sellerID, Integer id) {
        this.name = name;
        this.description = null;
        this.price = price;
        this.location = location;
//        location.addNewItem(this);
        this.id = id;
        this.sellerID = sellerID;
        this.buyer = null;
        this.availability = true;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }

    public Residence getLocation() {
        return this.location;
    }

    public Integer getId() {
        return id;
    }

    public Long getSellerID() {
        return this.sellerID;
    }

    public Buyer getBuyer() {
        return this.buyer;
    }

    public boolean isAvailable() {
        return this.availability;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    // MODIFIES : this
    // EFFECTS : change the availability from available to not available and vice versa.
    public void switchAvailability() {
        this.availability = !(this.availability);
    }

    // MODIFIES : this
    // EFFECTS : set the item id to something unique between all other items in the location
    public Integer setID() {
        Random rnd = new Random();
        int number = rnd.nextInt(99999);
        return parseInt(String.format("%06d", number));
    }

    // MODIFIES : this
    // EFFECTS : the buyer b will buy the item if it's available; so it
    // becomes unavailable and take b as its buyer.
    public void buy(Buyer b) {
        if (this.availability) {
            this.switchAvailability();
            this.setBuyer(b);
        }
    }

    // EFFECTS : make a json object from an item
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("name", name);
        jsonObject.put("description", description);
        jsonObject.put("location", location.getName());
        jsonObject.put("price",  price);
        jsonObject.put("ID", id);
        jsonObject.put("avail?", availability);
        jsonObject.put("Seller ID", sellerID);

        return jsonObject;
    }


}
