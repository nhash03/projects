package persistence;

import org.json.JSONObject;

public abstract class Writable {
    public abstract JSONObject toJson();
}
