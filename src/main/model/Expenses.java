package model;

//Represents an expense with an ID,date, decription ,category and amount(in dollars)

public class Expenses {

    private int expenseId;
    private String date;
    private double amount;
    private String category;
    private String decription;

    //MODIFIES: this
    //EFFECTS: constructs an expense with an ID,date, decription ,category and amount

    public Expenses(int expenseId,double amount,String date,String category,String description) {
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
}

