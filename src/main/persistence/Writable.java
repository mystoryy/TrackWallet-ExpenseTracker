package persistence;

import org.json.JSONObject;

public interface Writable {
    //EFFECTS: returns this as Json Object
    JSONObject toJson();
}
