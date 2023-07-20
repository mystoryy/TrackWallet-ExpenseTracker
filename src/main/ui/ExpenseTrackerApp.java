package ui;

import model.ExpenseTracker;
import model.Expenses;
import model.Income;

import java.util.Scanner;
//Expense Tracker Application
//Modelled some methods and structure from Teller App repository provided in the course

public class ExpenseTrackerApp {
    private ExpenseTracker expenseTracker;
    private double totalExpenditure;
    private double totalIncome;

    private static Scanner sc = new Scanner(System.in);

    // runs the ExpenseTrackerApp
    public ExpenseTrackerApp() {
        expenseTracker = new ExpenseTracker();
        runExpenseTrackerApp();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    public void runExpenseTrackerApp() {
        String input = "none";
        while (!input.equals("e")) {
            printMenu();
            input = sc.next();
            navigator(input);
        }
        if (!input.equals("e")) {
            System.exit(0);
        }

    }
    //MODIFIES: this
    //EFFECTS: navigates and processes the user input

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
//        } else if (sc.equals("7")) {
//            editIncome();
//        } else if (sc.equals("8")) {
//            editExpenses();
        } else if (sc.equals("9")) {
            viewCategoryWise();
        } else if (sc.equals("s")) {
            viewSavings();
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
//        System.out.println("Enter 7 to Edit Income");
//        System.out.println("Enter 8 to Edit Expenses");
        System.out.println("Enter 9 to view category wise Expenses");
        System.out.println("Enter s to view savings");
        System.out.println("Type e to exit");
    }


    //MODIFIES: this
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
    //MODIFIES: this
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

        System.out.println("       INCOME    ");
        System.out.println("\n========================================================================");
        for (Income income : expenseTracker.getIncomeList()) {
            System.out.println("Income ID: " + income.getIncomeID() + "\n" + "Amount: $" + income.getIncomeAmount()
                    + "\n" + "Description: " + income.getDescription() + "\n\n");
        }
        viewTotalIncome();
        String formatIncome = String.format("%.2f", totalIncome);

        System.out.println("Total Income: $" + formatIncome);
        System.out.println("========================================================================\n");
    }

    ////EFFECTS: displays the list of expense to the user with total expenses

    private void viewExpenses() {

        System.out.println(" \n         EXPENSE     ");
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
            System.out.println("Income deleted successfully");
        } else {
            System.out.println("Enter a correct income ID");
        }

    }
    //MODIFIES:this
    //EFFECTS: removes expense with selected expenseID from the list of expenses

    private void removeExpenses() {
        System.out.println("Enter the expense ID to delete: ");
        int id = sc.nextInt();
        if (expenseTracker.removeExpense(id)) {
            expenseTracker.removeExpense(id);
            System.out.println("Expense with given ID is deleted");
        } else {
            System.out.println("Enter a correct expense ID");
        }
    }

    //EFFECTS: displays the expenses with the preferred category in the the expense list

    private void viewCategoryWise() {
        System.out.println("\n Enter the Category to view expenses in: ");
        String category = sc.next();
        System.out.println("\n========Expenses for "  + category + " Category========");
        for (Expenses expense : expenseTracker.getExpensesList()) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                System.out.println("Expense ID: " + expense.getId() + "\n" + "Amount: $" + expense.getAmount() + "\n"
                        + "Date: " + expense.getDate()
                        + "\n" + "Category: " + expense.getCategory() + "\n" + "Description: " + expense.getDecription()
                        + "\n");
                //totalExpenditure += expense.getAmount();
               // String formatExpense = String.format("%.2f",totalExpenditure);
               // System.out.println("Total Expenditure for " + category + " Category is: $" + formatExpense);
            }

        }
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
        for (Income income: expenseTracker.getIncomeList()) {
            totalIncome += income.getIncomeAmount();
        }
    }
    //MODIFIES: this
    //EFFECTS: displays savings by deducting the amount of entered expenses from the income amount

    private void viewSavings() {
        viewTotalIncome();
        viewTotalExpenditure();

        double savings = totalIncome - totalExpenditure;
        String formatIncome = String.format("%.2f",savings);
        System.out.println("Your Savings Are: " + "$" + formatIncome + "\n");
    }

    //private static void editIncome() {

   // }

    //private static void editExpenses() {
    //}









}





