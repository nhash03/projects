package model;

import java.util.HashMap;

// Representing a login system that takes a username, password and residence and
// manage the seller or buyer account.
public class LoginSystem {
    private Residence userResidence;
    private String username;
    private String password;

    // REQUIRES : length of username and password > 0
    // MODIFIES : this
    // EFFECTS : make a potential user with give username and password
    // in the given residence
    public LoginSystem(String userResidence, String username, String password, Residences residences) {
        this.userResidence = Residence.parseToRes(userResidence, residences);
        this.password = password;
        this.username = username;
    }

    public LoginSystem(Residence residence, String username, String password) {
        this.userResidence = residence;
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Residence getUserResidence() {
        return userResidence;
    }

    public String getPassword() {
        return password;
    }

    // EFFECTS : shows if the username(return -2) doesn't exist or the username and password
    // do not match (return -1) or returns the index pointed to the buyer
    public Integer buyerLogin() {
        HashMap<String, String> listOfUsers = userResidence.getBuyersAccounts();
        int index = 0;
        for (String un : listOfUsers.keySet()) {
            if (this.username.equals(un)) {
                if (this.password.equals(listOfUsers.get(un))) {
                    return index;
                } else {
                    return -1;
                }
            }
            index++;
        }
        return -2;
    }

    // EFFECTS : shows if the username(return -2) doesn't exist or the username and password
    // do not match (return -1) or returns the index pointed to the seller
    public Integer sellerLogin() {
        HashMap<String, String> listOfUsers = userResidence.getSellersAccounts();
        int index = 0;
        for (String un : listOfUsers.keySet()) {
            if (this.username.equals(un)) {
                if (this.password.equals(listOfUsers.get(un))) {
                    return index;
                } else {
                    return -1;
                }
            }
            index++;

        }
        return -2;
    }


}