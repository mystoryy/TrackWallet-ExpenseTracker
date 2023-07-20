package model;

public class Income {
    private  double incomeAmount;
    private int incomeID;
    private String description;

    //EFFECTS: constructs an income with an ID, decription and amount
    public Income(int incomeID,double income,String description) {
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
}

