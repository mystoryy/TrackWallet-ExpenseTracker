package ui;

import model.ExpenseTracker;
import model.Expenses;
import model.Income;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Modelled some methods and structure from Teller App repository provided in the course
//Modelled code from JsonSerializationDemo repository given as resource for phase 2
//Expense Tracker Application
public class ExpenseTrackerApp {
    private static final String JSON_STORE = "./data/ExpenseTracker.json";
    private ExpenseTracker expenseTracker;
    private double totalExpenditure;
    private double totalIncome;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    private static Scanner sc = new Scanner(System.in);

    //EFFECTS: constructs the expenseTracker and runs the application
    public ExpenseTrackerApp() throws FileNotFoundException {
        expenseTracker = new ExpenseTracker("J's Expense Tracker");
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        runExpenseTrackerApp();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    public void runExpenseTrackerApp() {
        System.out.println("Welcome to Expense Tracker ! \nTrack-Save-Repeat.\n");
        String input = "none";
        while (!input.equals("e")) {
            printMenu();
            input = sc.next();
            navigator(input);
        }
        if (input.equals("e")) {
            System.out.println("\nYour savings are waiting for spending!\nSee you soon!!");
            System.exit(0);

        }
    }

    //MODIFIES: this
    //EFFECTS: navigates and processes the user input to do the functions
    public void navigator(String sc) {
        if (sc.equals("1")) {
            addIncome();
        } else if (sc.equals("2")) {
            addExpenses();
        } else if (sc.equals("3")) {
            viewIncome();
        } else if (sc.equals("4")) {
            viewExpenses();
        } else if (sc.equals("5")) {
            removeIncome();
        } else if (sc.equals("6")) {
            removeExpenses();
        } else if (sc.equals("7")) {
            viewCategoryWise();
        } else if (sc.equals("8")) {
            viewSavings();
        } else if (sc.equals("s")) {
            saveExpenseTracker();
        } else if (sc.equals("l")) {
            loadExpenseTracker();
        }
    }


    //EFFECTS: displays the options menu to the user
    public void printMenu() {
        System.out.println("Enter 1 to add Income");
        System.out.println("Enter 2 to add Expenses");
        System.out.println("Enter 3 to view Income");
        System.out.println("Enter 4 to view Expenses");
        System.out.println("Enter 5 to remove Income");
        System.out.println("Enter 6 to remove Expenses");
        System.out.println("Enter 7 to view category wise Expenses");
        System.out.println("Enter 8 to view savings");
        System.out.println("Enter s to save Expense Tracker");
        System.out.println("Enter l to load Expense Tracker");
        System.out.println("Enter e to exit");
    }


    //MODIFIES: this,income list
    //EFFECTS: adds income with amount and description to the income list
    private void addIncome() {
        System.out.println("Enter income amount: ");
        double incomeAdd = sc.nextDouble();

        System.out.println("Enter the description of the income");
        String describe = sc.next();

        Income isIncomeAdded = new Income(expenseTracker.getNumberOfIncome() + 1, incomeAdd, describe);
        expenseTracker.addIncome(isIncomeAdded);
        System.out.println("Income is added successfully");
        System.out.println("\n\n");
    }

    //MODIFIES: this,expense list
    //EFFECTS: adds expenses with amount,date,category and description to the expense list and gives expenseID to each
    //         expense added to the list
    public void addExpenses() {
        System.out.println("Enter Expenses Amount : ");
        double expenseAmount = sc.nextDouble();
        System.out.println("Enter the date(yyyy-mm-dd) of the Expense: ");
        String date = sc.next();
        System.out.println("Enter the category of the Expense: ");
        String category = sc.next();
        System.out.println("Enter the description of the Expense: ");
        String description = sc.next();

        Expenses expense = new Expenses(expenseTracker.getNumberOfExpenses() + 1, expenseAmount, date,
                category, description);
        expenseTracker.addExpenses(expense);
        System.out.println("Expense added successfully \n\n");

    }

    //EFFECTS: displays the list of Income to the user with total income
    private void viewIncome() {

        System.out.println("                                INCOME                                   ");
        System.out.println("========================================================================");
        for (Income income : expenseTracker.getIncomeList()) {
            System.out.println("Income ID: " + income.getIncomeID() + "\n" + "Amount: $" + income.getIncomeAmount()
                    + "\n" + "Description: " + income.getDescription() + "\n");
        }
        viewTotalIncome();
        String formatIncome = String.format("%.2f", totalIncome);

        System.out.println("Total Income: $" + formatIncome);
        System.out.println("========================================================================\n");
    }

    //EFFECTS: displays the list of expense to the user with total expenses
    private void viewExpenses() {

        System.out.println(" \n                           EXPENSES                                ");
        System.out.println("======================================================================");

        for (Expenses expenses : expenseTracker.getExpensesList()) {
            System.out.println("Expense ID: " + expenses.getId() + "\n" + "Amount: $" + expenses.getAmount()
                    + "\n" + "Date: " + expenses.getDate()
                    + "\n" + "Category: " + expenses.getCategory() + "\n" + "Description: " + expenses.getDecription()
                    + "\n");
        }
        viewTotalExpenditure();
        String formatExpense = String.format("%.2f", totalExpenditure);


        System.out.println("Total Expenditure: $" + formatExpense);
        System.out.println("======================================================================\n");
    }

    //MODIFIES: this,incomeList
    //EFFECTS: removes income with selected incomeID from the list of Incomes
    private void removeIncome() {
        System.out.println("Enter the index of income to delete: ");
        int incomeID = sc.nextInt();
        if (expenseTracker.removeIncome(incomeID)) {
            expenseTracker.removeIncome(incomeID);
            System.out.println("Income deleted successfully \n");
        } else {
            System.out.println("Enter a correct income ID \n");
        }

    }

    //MODIFIES:this
    //EFFECTS: removes expense with selected expenseID from the list of expenses
    private void removeExpenses() {
        System.out.println("Enter the expense ID to delete: ");
        int id = sc.nextInt();
        if (expenseTracker.removeExpense(id)) {
            expenseTracker.removeExpense(id);
            System.out.println("Expense with given ID is deleted \n");
        } else {
            System.out.println("Enter a correct expense ID \n");
        }
    }

    //MODIFIES: this
    //EFFECTS: displays the expenses with the preferred category in the expense list
    private void viewCategoryWise() {
        double totalSpending = 0;
        System.out.println("\n Enter the Category to view expenses in: ");
        String category = sc.next();
        System.out.println("\n===============Expenses for " + category + " Category==============");
        for (Expenses expense : expenseTracker.getExpensesList()) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                System.out.println("Expense ID: " + expense.getId() + "\n" + "Amount: $" + expense.getAmount() + "\n"
                        + "Date: " + expense.getDate()
                        + "\n" + "Category: " + expense.getCategory() + "\n" + "Description: " + expense.getDecription()
                        + "\n");
                totalSpending += expense.getAmount();

            }

        }
        String formatExpense = String.format("%.2f", totalSpending);


        System.out.println("Total Expenditure: $" + formatExpense);
        System.out.println("====================================================\n");

    }

    //MODIFIES: this
    //EFFECTS: gives total expenses by adding the expenses in the expense list
    private void viewTotalExpenditure() {
        this.totalExpenditure = 0;
        for (Expenses expenses : expenseTracker.getExpensesList()) {
            totalExpenditure += expenses.getAmount();
        }
    }

    //MODIFIES: this
    //EFFECTS: gives total income by adding the income in the income list
    private void viewTotalIncome() {
        this.totalIncome = 0;
        for (Income income : expenseTracker.getIncomeList()) {
            totalIncome += income.getIncomeAmount();
        }
    }

    //MODIFIES: this
    //EFFECTS: displays savings by deducting the amount of entered expenses from the income amount
    private void viewSavings() {
        viewTotalIncome();
        viewTotalExpenditure();

        double savings = totalIncome - totalExpenditure;
        String formatIncome = String.format("%.2f", savings);
        System.out.println("\n===============================\n");
        System.out.println("Your Savings Are: " + "$" + formatIncome + "\n");
        System.out.println("===============================\n");
    }


    // EFFECTS: saves expenseTracker to file
    private void saveExpenseTracker() {
        try {
            jsonWriter.open();
            jsonWriter.write(expenseTracker);
            jsonWriter.close();
            System.out.println("Saved " + expenseTracker.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads expenseTracker from file
    private void loadExpenseTracker() {
        try {
            expenseTracker = jsonReader.read();
            System.out.println("Loaded " + expenseTracker.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}



