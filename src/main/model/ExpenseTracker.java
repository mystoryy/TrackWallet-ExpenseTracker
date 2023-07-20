package model;

import java.util.ArrayList;
import java.util.List;

public class ExpenseTracker {
    private List<Income> incomeList;
    private List<Expenses> expensesList;

    //MODIFIES: this
    //EFFECTS: constructs an expensetracker with empty income list and expense list
    public ExpenseTracker() {
        incomeList = new ArrayList<>();
        expensesList = new ArrayList<>();
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

    //MODIFIES: this
    //EFFECTS: removes the expense with the given expense id from the expense list

    public boolean removeExpense(int id) {
        for (Expenses expense : expensesList) {
            if (expense.getId() == id) {
                expensesList.remove(expense);
                return true;
            }
        }
        return false;
    }
    //MODIFIES: this
    //EFFECTS: removes the income with the given income ID from the income list

    public boolean removeIncome(int id) {
        for (Income income : incomeList) {
            if (income.getIncomeID() == id) {
                incomeList.remove(income);
                return true;
            }
        }
        return false;
    }

}