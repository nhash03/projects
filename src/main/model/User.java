package model;

// Representing a user who lives in a specific residence,with name,account balance (in CAD) , personal
// username and password.
abstract class User {
    protected String name;
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
}
