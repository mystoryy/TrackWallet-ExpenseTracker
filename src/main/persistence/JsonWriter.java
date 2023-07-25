package persistence;

import model.ExpenseTracker;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//Represents a writer that writes Json representation of ExpenseTracker to file
//Modelled code from JsonSerializationDemo repository given as resource for phase 2
public class JsonWriter {
    private static final  int TAB = 4;
    private PrintWriter writer;
    private String destination;


    //EFFECTS:constructs writer to write to destination file
    public JsonWriter(String destination) {

        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(ExpenseTracker et) {
        JSONObject json = et.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

}