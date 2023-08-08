package model;

import org.json.JSONObject;
import persistence.Writable;

//Represents an expense with an ID,date, description ,category and amount(in dollars)
public class Expenses implements Writable {

    private int expenseId;
    private String date;
    private double amount;
    private String category;
    private String decription;


    //EFFECTS: constructs an expense with an ID,date, description ,category and amount
    public Expenses(int expenseId, double amount, String date, String category, String description) {
        this.expenseId = expenseId;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.decription = description;
    }

    public double getAmount() {
        return amount;
    }


    public int getId() {
        return expenseId;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }


    public String getDecription() {
        return decription;
    }

    //EFFECTS: returns JSON object representation of the Expense Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Expense ID", expenseId);
        json.put("Expense Amount", amount);
        json.put("Date", date);
        json.put("Category", category);
        json.put("Description", decription);
        return json;
    }

}

