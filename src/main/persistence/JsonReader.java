package persistence;


import model.ExpenseTracker;
import model.Expenses;
import model.Income;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//Modelled code from JsonSerializationDemo- "https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git"
// given" as resource for phase 2
// Represents a reader that reads ExpenseTracker from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {

        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ExpenseTracker read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseExpenseTracker(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private ExpenseTracker parseExpenseTracker(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ExpenseTracker et = new ExpenseTracker(name);
        addincomes(et, jsonObject);
        addexpenses(et, jsonObject);
        return et;
    }

    // MODIFIES: et
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addexpenses(ExpenseTracker et, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Expenses List");
        for (Object json : jsonArray) {
            JSONObject nextexpense = (JSONObject) json;
            addExpense(et, nextexpense);
        }
    }

    // MODIFIES: et
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addincomes(ExpenseTracker et, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Income List");
        for (Object json : jsonArray) {
            JSONObject nextincome = (JSONObject) json;
            addIncome(et, nextincome);
        }
    }

    // MODIFIES: et
    // EFFECTS: parses expense from JSON object and adds it to ExpenseTracker
    private void addExpense(ExpenseTracker et, JSONObject jsonObject) {
        int expenseID = jsonObject.getInt("Expense ID");
        double amount = jsonObject.getDouble("Expense Amount");
        String date = jsonObject.getString("Date");
        String category = jsonObject.getString("Category");
        String description = jsonObject.getString("Description");
        Expenses expense = new Expenses(expenseID, amount, date, category, description);
        et.addExpenses(expense);
    }

    // MODIFIES: et
    // EFFECTS: parses income from JSON object and adds it to ExpenseTracker
    private void addIncome(ExpenseTracker et, JSONObject jsonObject) {
        int incomeID = jsonObject.getInt("Income ID");
        double incomeAmount = jsonObject.getDouble("Income Amount");
        String description = jsonObject.getString("Description");
        Income income = new Income(incomeID, incomeAmount, description);
        et.addIncome(income);
    }


}
