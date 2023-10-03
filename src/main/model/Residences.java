package model;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// Representing the society of each 4 residence
public class Residences {
    static List<Residence> residences;

    // EFFECTS : make a community of 4 residences
    public Residences() {
        residences = new ArrayList<>();
        residences.add(new Residence("Walter Gage"));
        residences.add(new Residence("Exchange"));
        residences.add(new Residence("Totem Park"));
        residences.add(new Residence("Place Vanier"));
    }

    public List<Residence> getResidences() {
        return residences;
    }

    // EFFECTS : make a json object from residences
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Walter Gage", residences.get(0).toJson());
        json.put("Exchange", residences.get(1).toJson());
        json.put("Totem Park", residences.get(2).toJson());
        json.put("Place Vanier", residences.get(3).toJson());
        return json;
    }
}
