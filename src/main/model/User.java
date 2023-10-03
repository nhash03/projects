package model;

import org.json.JSONObject;

// Representing a user who lives in a specific residence,with name,account balance (in CAD) , personal
// username and password.
abstract class User {
    protected String name;
    long id;
    protected Residence location;
    protected String username;
    protected String password;
    protected double balance;

    // REQUIRES: length of name, username and password > 0 and unique
    //           username and password
    // EFFECTS : balance is set to 0; user's username and pass
    //           are personal; The user is living in a given residence.
    public User(String nm, Residence res, String un, String pass) {
        name = nm;
        location = res;
        username = un;
        password = pass;
        id = setId();
        balance = 0;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public Residence getLocation() {
        return location;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setLocation(Residence location) {
        this.location = location;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long setId() {
        return (name + username).hashCode();
    }

    public long getId() {
        return id;
    }

    // EFFECTS : make a json object from the user
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", id);
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        jsonObject.put("name", name);
        jsonObject.put("location", location.getName());
        jsonObject.put("balance", balance);

        return jsonObject;
    }
}
