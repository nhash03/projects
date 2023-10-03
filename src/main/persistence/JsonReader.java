
package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import model.*;
import org.json.*;

// Represents a reader that reads residences from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads residences from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Residences read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseResidences(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses residences from JSON object and returns it
    private Residences parseResidences(JSONObject jsonObject) {
        Residences residences = new Residences();
        addResidences(residences, jsonObject);
        return residences;
    }

    // MODIFIES: Residences
    // EFFECTS: parses residence from JSON object and adds them to residences
    private void addResidences(Residences rs, JSONObject jsonObject) {
        JSONObject wgObj = jsonObject.getJSONObject("Walter Gage");
        JSONObject exObj = jsonObject.getJSONObject("Exchange");
        JSONObject tpObj = jsonObject.getJSONObject("Totem Park");
        JSONObject pvObj = jsonObject.getJSONObject("Place Vanier");
        List<String> names = new ArrayList<>();
        names.add("Walter Gage");
        names.add("Exchange");
        names.add("Totem Park");
        names.add("Place Vanier");
        List<JSONObject> residenceObjs = new ArrayList<>(4);
        residenceObjs.add(wgObj);
        residenceObjs.add(exObj);
        residenceObjs.add(tpObj);
        residenceObjs.add(pvObj);
        int j = 0;
        for (JSONObject jsonObj : residenceObjs) {
            addResItems(jsonObj.getJSONArray("resItems"), rs,j);
            addResBuyers(jsonObj.getJSONArray("resBuyers"), rs,j);
            addResSellers(jsonObj.getJSONArray("resSellers"), rs,j);
            addResBuyAcc(jsonObj.getJSONObject("buyerAccounts"), rs,j);
            addResSellAcc(jsonObj.getJSONObject("sellerAccounts"), rs,j);
            j++;
        }
    }

    // MODIFIES: Residence
    // EFFECTS: parses residence fields from JSON object and adds them to residence
    private void addResItems(JSONArray jsonArray, Residences rs, Integer j) {
        for (Object obj: jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            String name = jsonObject.optString("name");
            String description = jsonObject.optString("description");
            String locName = jsonObject.optString("location");
            double price = jsonObject.optDouble("price");
            Integer id = jsonObject.optInt("ID");
            Boolean isAvail = jsonObject.optBoolean("avail?");
            Long sellerID = jsonObject.optLong("Seller ID");
            Item i = new Item(name, price, rs.getResidences().get(j), sellerID, id);
            i.setDescription(description);
            i.setAvailability(isAvail);
            rs.getResidences().get(j).getItems().add(i);
        }
    }

    // MODIFIES: Buyer
    // EFFECTS: parses buyer fields from JSON object and adds them to buyer
    private void addResBuyers(JSONArray jsonArray, Residences rs, Integer j) {
        List<Buyer> buyers = new ArrayList<>();
        for (Object obj: jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            long id = jsonObject.optLong("id");
            String username = jsonObject.optString("username");
            String password = jsonObject.optString("password");
            String name = jsonObject.optString("name");
            String location = jsonObject.optString("location");
            double balance = jsonObject.optDouble("balance");
            List<Item> alreadyBought = itemIDtoItem(jsonObject.getJSONArray("items"), rs, j);
            Buyer b = new Buyer(name, rs.getResidences().get(j), username, password);
            b.setAlreadyBought(alreadyBought);
            System.out.println(alreadyBought);
            b.setBalance(balance);
            buyers.add(b);
        }
        rs.getResidences().get(j).setBuyers(buyers);
    }

    // MODIFIES: Item
    // EFFECTS: parses item fields from JSON object and adds them to Item
    private List<Item> itemIDtoItem(JSONArray itemIDs, Residences rs, Integer j) {
        List<Item> items = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        List<Item> resItems = rs.getResidences().get(j).getItems();
        List<Integer> resIDs = new ArrayList<>();
        for (Item i: resItems) {
            resIDs.add(i.getId());
        }
        for (int i = 0; i < itemIDs.length(); i++) {
            ids.add(itemIDs.getInt(i));
        }
        for (Integer id: ids) {
            Integer index = ids.indexOf(id);
            items.add(resItems.get(index));
        }
        return items;
    }

    // MODIFIES: Sellers
    // EFFECTS: parses seller fields from JSON object and adds them to Seller
    private void addResSellers(JSONArray jsonArray, Residences rs, Integer j) {
        List<Seller> sellers = new ArrayList<>();
        for (Object obj: jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            long id = jsonObject.optLong("id");
            String username = jsonObject.optString("username");
            String password = jsonObject.optString("password");
            String name = jsonObject.optString("name");
            String location = jsonObject.optString("location");
            double balance = jsonObject.optDouble("balance");
            List<Item> itemsToCell = itemIDtoItem(jsonObject.getJSONArray("items"), rs, j);
            Seller s = new Seller(name, rs.getResidences().get(j), username, password);
            s.setItemsToSell(itemsToCell);
            sellers.add(s);
        }
        rs.getResidences().get(j).setSellers(sellers);
    }

    // MODIFIES: Sellers account
    // EFFECTS: parses seller username and password from JSON object and adds them to seller accounts
    private void addResSellAcc(JSONObject jsonObject, Residences rs, Integer j) {
        Iterator<String> usernames = jsonObject.keys();
        HashMap<String, String> sellersAccounts = new HashMap<>();
        while (usernames.hasNext()) {
            String username = usernames.next();
            String password = jsonObject.getString(username);
            sellersAccounts.put(username, password);
        }
        rs.getResidences().get(j).setSellersAccounts(sellersAccounts);
    }

    // MODIFIES: Buyer account
    // EFFECTS: parses buyer username and password from JSON object and adds them to buyer accounts
    private void addResBuyAcc(JSONObject jsonObject, Residences rs, Integer j) {
        Iterator<String> usernames = jsonObject.keys();
        HashMap<String, String> buyersAccounts = new HashMap<>();
        while (usernames.hasNext()) {
            String username = usernames.next();
            String password = jsonObject.getString(username);
            buyersAccounts.put(username, password);
        }
        rs.getResidences().get(j).setBuyersAccounts(buyersAccounts);
    }
}
