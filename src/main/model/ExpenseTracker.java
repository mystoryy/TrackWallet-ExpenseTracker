package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represents the lists of income and expenses along with methods to add and remove items from the lists
public class ExpenseTracker implements Writable {
    private List<Income> incomeList;
    private List<Expenses> expensesList;
    private String name;


    //EFFECTS: constructs an expensetracker with a name and empty income list and expense list
    public ExpenseTracker(String name) {
        this.name = name;
        incomeList = new ArrayList<>();
        expensesList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Expenses> getExpensesList() {
        return expensesList;
    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

    //MODIFIES: this
    //EFFECTS:adds income to the income list
    public void addIncome(Income i) {
        incomeList.add(i);
    }

    //MODIFIES: this
    //EFFECTS:adds expense to the expense list
    public void addExpenses(Expenses e) {
        expensesList.add(e);
    }

    //EFFECTS: gives the size of the expense list providing the number of expenses added
    public int getNumberOfExpenses() {
        return expensesList.size();
    }

    //EFFECTS: gives the size of the income list providing the number of income added
    public int getNumberOfIncome() {
        return incomeList.size();
    }

    //MODIFIES: this, expenselist
    //EFFECTS: removes the expense with the given expense id from the expense list and returns true if removed from list
    //         false otherwise
    public boolean removeExpense(int id) {
        for (Expenses expense : expensesList) {
            if (expense.getId() == id) {
                expensesList.remove(expense);
                return true;
            }
        }
        return false;
    }

    //MODIFIES: this,incomelist
    //EFFECTS: removes the income with the given income ID from the income list and returns true if removed from list
    //         false otherwise
    public boolean removeIncome(int id) {
        for (Income income : incomeList) {
            if (income.getIncomeID() == id) {
                incomeList.remove(income);
                return true;
            }
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("Expenses List", expensesToJson());
        json.put("Income List", incomeToJson());
        return json;
    }

    // EFFECTS: returns expenses in this expenseTracker as a JSON array
    private JSONArray expensesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Expenses e : expensesList) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns income in this expenseTracke as a JSON array
    private JSONArray incomeToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Income i : incomeList) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }

}
