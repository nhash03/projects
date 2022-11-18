package ui;


import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static java.lang.Integer.parseInt;

// Residence Market application
public class MarketApp {
    private static final String JSON_STORE = "./data/Residences.json";
    private Residences residences;
    private Buyer buyer;
    private Seller seller;
    private Scanner input;
    private Residence walterGage;
    private Residence exchange;
    private Residence totemPark;
    private Residence placeVanier;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    boolean stillRunning = true;

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Seller getSeller() {
        return seller;
    }

    public Residence getWalterGage() {
        return walterGage;
    }

    // EFFECTS : tuns the market application
    public MarketApp() throws IOException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        residences = new Residences();
        //input = new Scanner(System.in);
        init();
//        runMarket();
    }

    // MODIFIES : this
    // EFFECTS : show the main user menu to choose between Buyer or Seller or quit
    private void runMarket() throws IOException {

        String command = null;
        input = new Scanner(System.in);

        while (stillRunning) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                stillRunning = false;
            } else {
                processing(command);
            }
        }
        saveResidences();
        System.out.println("See you later! Byeeee!");
    }

    // MODIFIES : this
    // EFFECTS : initializes the residences
    private void init() {
        //input.useDelimiter("\n");

        loadResidences();
        walterGage = residences.getResidences().get(0);
        placeVanier = residences.getResidences().get(3);
        exchange = residences.getResidences().get(1);
        totemPark = residences.getResidences().get(2);
    }

    // EFFECTS : displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWhat type of user are you ?");
        System.out.println("\tb -> Buyer");
        System.out.println("\ts -> Seller");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES : this
    // EFFECTS : processes user command and see if the user already exists or not
    private void processing(String command) throws IOException {
        if (command.equals("b")) {
            System.out.println("Are you an existing buyer? Type 'yes' or 'no'.");
            String isExistingBuyer = input.next();
            isExistingBuyer = isExistingBuyer.toLowerCase();
            if (isExistingBuyer.contains("yes")) {
                handleBuyerLoggingIn();
            } else if (isExistingBuyer.contains("no")) {
                displayBuyerSignIn();
            } else {
                System.out.println("Returning to the previous menu!");
            }
        } else if (command.equals("s")) {
            System.out.println("Are you an existing seller? Type 'yes' or 'no'.");
            String isExistingSeller = input.next();
            isExistingSeller = isExistingSeller.toLowerCase();
            if (isExistingSeller.contains("yes")) {
                handleSellerLoggingIn();
            } else if (isExistingSeller.contains("no")) {
                displaySellerSignIn();
            }
        } else {
            System.out.println("Please enter a valid input!!");
        }
    }

    // MODIFIES : this
    // EFFECTS : processes logging in information of a seller
    private void handleSellerLoggingIn() throws IOException {
        System.out.println("\nPlease enter your username: ");
        String username = input.next();
        System.out.println("\nPlease enter your password: ");
        String password = input.next();
        System.out.println("\nWhere is your residence? ");
        System.out.println("\te -> Exchange");
        System.out.println("\tw -> Walter Gage");
        System.out.println("\tt -> Totem Park");
        System.out.println("\tp -> Place Vanier");
//        System.out.println("\tNA -> residence not in the list");
        String residenceCommand = input.next();
        Residence residence = handleResidence(residenceCommand);
        LoginSystem sellerLogin = new LoginSystem(residence, username, password);
        Integer loginIndex = sellerLogin.sellerLogin();
        handleSellerLoginIndex(loginIndex, sellerLogin.getUserResidence().getSellers());
    }

    // MODIFIES : this
    // EFFECTS : process the logging in based on the provided information
    // and returns if the seller exists in the residence or the information is incorrect
    private void handleSellerLoginIndex(Integer index, List<Seller> userList) throws IOException {
        if (index == -1 | index == -2) {
            System.out.println("Username and Password does not match.");
            System.out.println("Press 1 to register.");
            System.out.println("Press 2 to try again.");
            String choice = input.next();
            if (choice.equals("1")) {
                displaySellerSignIn();
            } else if (choice.equals("2")) {
                handleSellerLoggingIn();
            }
        } else {
            makeNewSellerAccount(userList.get(index));
        }
    }

    // MODIFIES : this
    // EFFECTS : processes logging in information of a buyer
    private void handleBuyerLoggingIn() throws IOException {
        System.out.println("\nPlease enter your username: ");
        String username = input.next();
        System.out.println("\nPlease enter your password: ");
        String password = input.next();
        System.out.println("\nWhere is your residence? ");
        System.out.println("\te -> Exchange");
        System.out.println("\tw -> Walter Gage");
        System.out.println("\tt -> Totem Park");
        System.out.println("\tp -> Place Vanier");
//        System.out.println("\tNA -> residence not in the list");
        String residenceCommand = input.next();
        Residence residence = handleResidence(residenceCommand);
        LoginSystem buyerLogin = new LoginSystem(residence, username, password);
        Integer loginIndex = buyerLogin.buyerLogin();
        handleBuyerLoginIndex(loginIndex, buyerLogin.getUserResidence().getBuyers());
    }

    // MODIFIES : this
    // EFFECTS : process the logging in based on the provided information
    // and returns if the buyer exists in the residence or the information is incorrect
    private void handleBuyerLoginIndex(Integer index, List<Buyer> userList) throws IOException {
        if (index == -1 | index == -2) {
            System.out.println("Username and Password does not match.");
            System.out.println("Press 1 to register.");
            System.out.println("Press 2 to try again.");
            String choice = input.next();
            if (choice.equals("1")) {
                runMarket();
            } else if (choice.equals("2")) {
                handleBuyerLoggingIn();
            }
        } else {
            makeNewBuyerAccount(userList.get(index));
        }
    }

    // MODIFIES : this
    // EFFECTS : displays a signing in form for a new buyer and create a new buyer account
    private void displayBuyerSignIn() throws IOException {
//        input = new Scanner(System.in);
        System.out.println("\nPlease enter your name: ");
        String name = input.next();

        System.out.println("\nPlease enter your personal username: ");
        String username = input.next();

        System.out.println("\nPlease enter your personal password: ");
        String password = input.next();

        System.out.println("\nPlease select your residence: ");
        System.out.println("\te -> Exchange");
        System.out.println("\tw -> Walter Gage");
        System.out.println("\tt -> Totem Park");
        System.out.println("\tp -> Place Vanier");
        Residence residence;
        String residenceCommand = input.next().toLowerCase();
        residence = handleResidence(residenceCommand);
        buyer = new Buyer(name, residence, username, password);
        makeNewBuyerAccount(buyer);
    }

    // EFFECTS : returns the residence based on its initials
    public Residence handleResidence(String resCommand) {
        Residence res = null;
        switch (resCommand) {
            case("Exchange") :
                res = exchange;
                break;
            case("Walter Gage") :
                res = walterGage;
                break;
            case ("Totem Park") :
                res = totemPark;
                break;
            case ("Place Vanier") :
                res = placeVanier;
                break;
            default:
                System.out.println("\nYour residence is not in our list right now!!");
                System.out.println("Please register again!");
                //runMarket();
        }
        return res;
    }

    // MODIFIES : display the buyer page and provided options to a buyer
    private void makeNewBuyerAccount(Buyer b) throws IOException {
//        input = new Scanner(System.in);
        boolean stillShoppingMenu = true;

//        while (stillShoppingMenu) {
        System.out.println("\nWhat do you want to do? ");
        System.out.println("\t1 -> Visit the shopping bag");
        System.out.println("\t2 -> Visit previous purchases");
        System.out.println("\t3 -> Start shopping!");
        System.out.println("\t4 -> Set the balance of your account");
        System.out.println("\t5 -> Return to main menu");
        String option = input.next();

        if (option.equals("1")) {
            displayShoppingBag(b);
        } else if (option.equals("2")) {
            displayPreviousPurchases(b);
        } else if (option.equals("3")) {
            displayItemsToBuy(b);
        } else if (option.equals("4")) {
            displaySetBalance(b);
        } else if (option.equals("5")) {
            stillShoppingMenu = false;
            stillRunning = false;
        }
    }


    // MODIFIES : this
    // EFFECTS : display a page to set a new balance and set a new balance
    private void displaySetBalance(Buyer b) throws IOException {
        System.out.println("\nYour current balance is " + b.getBalance());
        System.out.println("How much do you want your balance to be?");
        String targetBalanceStr = input.next();
        double targetBalance = Double.parseDouble(targetBalanceStr);
        b.setBalance(targetBalance);
        makeNewBuyerAccount(b);
    }

    // EFFECTS : display items in the residence and display options of what can be done with items
    // The buyer can add it to the wishlist or return to the previous page.
    private void displayItemsToBuy(Buyer b) throws IOException {
        displayItemsInResidence(b.getLocation());
        System.out.println("\nWhat do you want to do right now?");
        System.out.println("\t1 -> Add items to the shopping bag");
        System.out.println("\t2 -> Return to the previous menu");
        String option = input.next();
        if (option.equals("1")) {
            System.out.println("Type the ID of each item you want in a single line.");
            System.out.println("Type 'finish' when you're done");
            addingItemsToBag(b);
        } else if (option.equals("2")) {
            makeNewBuyerAccount(b);
        } else {
            System.out.println("Invalid request!");
            displayItemsToBuy(b);
        }
    }

    // MODIFIES : this
    // EFFECTS : add item to the buyer's shopping list
    private void addingItemsToBag(Buyer b) throws IOException {
        String id = input.next();
        while (!id.contains("finish")) {
            addById(b, id);
            id = input.next();
        }
        makeNewBuyerAccount(b);
    }

    // EFFECTS : display only available items in the given residence
    private void displayItemsInResidence(Residence res) {
        List<Item> listOfItems = new ArrayList<>();
        for (Item i: res.getItems()) {
            if (i.isAvailable()) {
                listOfItems.add(i);
            }
        }
        displayListItem(listOfItems);
    }

    // EFFECTS : display a list of items in the given list
    private void displayListItem(List<Item> listOfItems) {
        if (listOfItems.size() == 0) {
            System.out.println("We are looking at an empty list !!!");
        } else {
            int j = 1;
            for (Item i : listOfItems) {
                System.out.println("\nItem " + j + " :");
                System.out.println("\tID: " + i.getId());
                System.out.println("\tName: " + i.getName());
                System.out.println("\tPrice: " + i.getPrice());
                System.out.println("\tAdditional notes: " + i.getDescription());
                System.out.println("--------------------------------------");
                j++;
            }
        }
    }

    // EFFECTS : display the previous purchases of the buyer
    private void displayPreviousPurchases(Buyer b) throws IOException {
//        input = new Scanner(System.in);
        displayListItem(b.getAlreadyBought());
        System.out.println("Press anything to return to the previous menu.");
        makeNewBuyerAccount(b);
    }

    // EFFECTS : display the buyer's wish list (shopping bag)
    private void displayShoppingBag(Buyer b) throws IOException {
        displayListItem(b.getItemsToBuy());
        handleShoppingBagMenu(b);
    }

    // EFFECTS : display options that can be done in the buyer's shopping bag : returning to the previous menu,
    // calculating sum of items' price and removing item
    private void handleShoppingBagMenu(Buyer b) throws IOException {
//        input = new Scanner(System.in);
        boolean stayOnPage = true;
        while (stayOnPage) {
            System.out.println("What do you want to do now? ");
            System.out.println("\t1 -> Return to the previous menu");
            System.out.println("\t2 -> See how much you should pay");
            System.out.println("\t3 -> Remove an item from the list");
            System.out.println("\t4 -> Pay for items");
            String option = input.next();
            if (option.equals("1")) {
                makeNewBuyerAccount(b);
                stayOnPage = false;
            } else if (option.equals("2")) {
                System.out.println("You should pay " + b.howMuchToPay() + " to buy all of the items.");
            } else if (option.equals("3")) {
                System.out.println("The ID of the item you want to remove: ");
                String id = input.next();
                removeByID(b, id);
            } else if (option.equals("4")) {
                b.payForItems();
                payingCommands(b);
            }
        }
    }

    // EFFECTS : print a command if you don't have enough balance to buy an item
    private void payingCommands(Buyer b) {
        for (Item i: b.getItemsToBuy()) {
            System.out.println("You don't have enough balance to buy " + i.getName());
        }
        System.out.println("Done!");
    }

    // MODIFIES : this
    // EFFECTS : remove an item with given ID if exists in the buyer's wish list
    public static void removeByID(Buyer b, String id) {
        try {
            int idInteger = parseInt(id);
            for (Item i: b.getItemsToBuy()) {
                if (i.getId() == idInteger) {
                    b.removeFromWishlist(i);
                    //System.out.println("Item " + i.getName() + " with ID " + id + " has successfully removed.");
                }
            }
        } catch (NumberFormatException nfe) {
            System.out.println("You should just enter integers!!");
            System.out.println("Try again!");
        }
    }

    // MODIFIES : this
    // EFFECTS : add an item with given ID if exists to the buyer's wish list
    public static void addById(Buyer b, String id) {
        try {
            int idInteger = parseInt(id);
            for (Item i: b.getLocation().getItems()) {
                if (i.getId().equals(idInteger)) {
                    b.addItemToBuy(i);
                    //System.out.println("Item " + i.getName() + " with ID " + id + " has successfully added.");
                }
            }
        } catch (NumberFormatException nfe) {
            System.out.println("You should just enter integers!!");
            System.out.println("Try again!");
        }

    }

    // MODIFIES : this
    // EFFECTS : displays a signing in form for a new seller and make a new seller account
    private void displaySellerSignIn() throws IOException {
//        in = new Scanner(System.in);
        System.out.println("\nPlease enter your name: ");
        String name = input.next();

        System.out.println("\nPlease enter your personal username: ");
        String username = input.next();

        System.out.println("\nPlease enter your personal password: ");
        String password = input.next();

        System.out.println("\nPlease select your residence: ");
        System.out.println("\te -> Exchange");
        System.out.println("\tw -> Walter Gage");
        System.out.println("\tt -> Totem Park");
        System.out.println("\tp -> Place Vanier");
        System.out.println("\tNA -> residence not in the list");
        String residenceCommand = input.next();
        Residence residence = handleResidence(residenceCommand);

        seller = new Seller(name, residence, username, password);
        makeNewSellerAccount(seller);
    }

    // MODIFIES : display the seller page and provided options to a seller
    private void makeNewSellerAccount(Seller seller) throws IOException {
//        options = new Scanner(System.in);
//        boolean stillSellingMenu = true;

//        while (stillSellingMenu) {
        System.out.println("\nWhat do you want to do? ");
        System.out.println("\t1 -> Add an item to the Residence's list");
        System.out.println("\t2 -> Modify previous items");
        System.out.println("\t3 -> Handle your account");
        System.out.println("\t4 -> Return to the main menu");
        String option = input.next();

        if (option.equals("1")) {
            displayAddingItem(seller);
        } else if (option.equals("2")) {
            displayModifyingItems(seller);
        } else if (option.equals("3")) {
            displayHandlingSellerAccount(seller);
        } else if (option.equals("4")) {
//                stillSellingMenu = false;
            stillRunning = false;
        }
    }
//    }

    // EFFECTS : display a page for managing a seller account
    private void displayHandlingSellerAccount(Seller seller) throws IOException {
        System.out.println("Hello " + seller.getName() + "!");
        System.out.println("How is your life at " + seller.getLocation().getName());
        displayItemsToSell(seller);
        System.out.println("Press 1 to return to the previous menu.");
        String choice = input.next();
        if (choice.equals("1")) {
            makeNewSellerAccount(seller);
        }
    }

    // EFFECTS : display items of a seller and ask for the items' ID that he wants to modify
    private void displayModifyingItems(Seller seller) throws IOException {
        displayItemsToSell(seller);
        System.out.println("Type the item IDs that you want to modify or 'finish' to return to the previous menu: ");
        String idToModify = input.next().toLowerCase();
        while (!idToModify.contains("finish")) {
            modifyById(seller, idToModify);
            System.out.println("Enter the next ID or 'finish':");
            idToModify = input.next();
        }
        makeNewSellerAccount(seller);
    }

    // EFFECTS : displays list of items that a seller has
    private void displayItemsToSell(Seller seller) {
        System.out.println("Here are your items: ");
        int j = 1;
        for (Item i : seller.getItemsToSell()) {
            System.out.println("\nItem " + j + " :");
            System.out.println("\tID: " + i.getId());
            System.out.println("\tName: " + i.getName());
            System.out.println("\tPrice: " + i.getPrice());
            System.out.println("\tAdditional notes: " + i.getDescription());
            System.out.println("\tAvailability: " + i.isAvailable());
            System.out.println("--------------------------------------");
            j++;
        }
    }

    // MODIFIES : this
    // EFFECTS : modify an item with the given ID (remove, change features and etc.)
    private void modifyById(Seller seller, String targetID) {
        for (Item i: seller.getItemsToSell()) {
            if (i.getId() == parseInt(targetID)) {
                System.out.println("Do you want to delete the item? Type 'yes' or 'no'.");
                String toDelete = input.next().toLowerCase();
                if (toDelete.contains("yes")) {
                    seller.deleteItem(i);
                    break;
                } else if (toDelete.contains("no")) {
                    System.out.println("New name for the item: ");
                    i.setName(input.next());
                    System.out.println("New price: ");
                    i.setPrice(Double.parseDouble(input.next()));
                    System.out.println("Adding a description: ");
                    i.setDescription(input.next());
                    System.out.println("Do you want to switch the availability? Type 'yes' or 'no'.");
                    String choice = input.next();
                    if (choice.equals("'yes'")) {
                        i.switchAvailability();
                    }
                }
            }
        }
        System.out.println("The item has successfully been modified!");
    }

    // MODIFIES : this
    // EFFECTS : display a form to a seller to add a specific item
    private void displayAddingItem(Seller seller) throws IOException {
        System.out.println("\nPlease fill out the form below:");
        System.out.println("Name of the item: ");
        String name = input.next();
        System.out.println("Price: ");
        String priceString = input.next();
        System.out.println("Any description: ");
        String description = input.next();
        Item item = new Item(name, Double.parseDouble(priceString), seller.getLocation(), seller.getId());
        item.setDescription(description);
        seller.addItemToSell(item);
//        seller.getLocation().addNewItem(item);
        System.out.println("Item " + item.getName() + " created and added to the available items in the residence.");
        System.out.println("\nWhat's next?");
        System.out.println("\t1 -> Add more items");
        System.out.println("\t2 -> Return to the previous menu");
        String option = input.next();
        if (option.equals("1")) {
            displayAddingItem(seller);
        } else if (option.equals("2")) {
            makeNewSellerAccount(seller);
        }
    }

    public void saveResidences() {
        try {
            jsonWriter.open();
            jsonWriter.write(residences);
            jsonWriter.close();
            System.out.println("Saved Residences to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadResidences() {
        try {
            residences = jsonReader.read();
            System.out.println("Loaded residences from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
