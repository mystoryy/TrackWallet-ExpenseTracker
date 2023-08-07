package model;

import org.json.JSONObject;
import persistence.Writable;

//Represents an income with an ID, description  and amount(in dollars)
public class Income implements Writable {
    private double incomeAmount;
    private int incomeID;
    private String description;


    //EFFECTS: constructs an income with an ID, description and amount
    public Income(int incomeID, double income, String description) {
        this.incomeID = incomeID;
        this.incomeAmount = income;
        this.description = description;
    }

    public double getIncomeAmount() {
        return incomeAmount;
    }

    public int getIncomeID() {
        return incomeID;
    }

    public String getDescription() {
        return description;
    }


    //todo comments
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Income ID", incomeID);
        json.put("Income Amount", incomeAmount);
        json.put("Description", description);
        return json;
    }
}

