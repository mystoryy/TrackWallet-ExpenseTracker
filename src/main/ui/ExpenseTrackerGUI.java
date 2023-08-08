package ui;

import model.ExpenseTracker;
import model.Expenses;
import model.Income;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

//Modelled some code from AlarmSystem repository given in lecture- "https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git"
//Expense Tracker Graphic Application and represents the main frame of gui
public class ExpenseTrackerGUI extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private static final String JSON_STORE = "./data/ExpenseTracker.json";
    private ExpenseTracker expenseTracker;
    private JPanel leftpanel;
    private JPanel rightpanel;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;


    //EFFECTS: creates expense tracker GUI with specific components and frame
    public ExpenseTrackerGUI() {
        super("TrackWallet - Expense Tracker");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        expenseTracker = new ExpenseTracker("Track Wallet");
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        leftpanel = new JPanel();
        leftpanel.setLayout(new BoxLayout(leftpanel, BoxLayout.Y_AXIS));

        rightpanel = new JPanel();
        rightpanel.setLayout(new BorderLayout());

        addButtonPanel();
        add(leftpanel, BorderLayout.WEST);
        add(rightpanel, BorderLayout.CENTER);
        setVisible(true);
        homeLabel();
    }

    //MODIFIES: this
    //EFFECTS: sets the welcome label to the home screen of the JFrame
    public void homeLabel() {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new GridBagLayout());
        JLabel welcomelabel = new JLabel("Welcome To Track Wallet!");
        welcomelabel.setFont(new Font("Apple Chancery", Font.BOLD, 36));


        JLabel subtitle = new JLabel("TRACK.SAVE.REPEAT");
        subtitle.setFont(new Font("Ayuthaya", Font.PLAIN, 24));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(-100, 0, 0, 0);
        constraints.anchor = GridBagConstraints.CENTER;
        welcomePanel.add(welcomelabel, constraints);
        constraints.gridy = 1;
        welcomePanel.add(subtitle, constraints);

        ImageIcon logo = new ImageIcon("data/trackwallet.png");
        Image logoImage = logo.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        logo = new ImageIcon(logoImage);

        JLabel logolabel = new JLabel(logo);
        constraints.gridx = 0;
        constraints.gridy = 0;
        welcomePanel.add(logolabel, constraints);

        rightpanel.add(welcomePanel, BorderLayout.CENTER);
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: adds buttons to the left panel of the JFrame
    private void addButtonPanel() {
        leftpanel.setLayout(new GridLayout(10, 1));
        addButton("Add Income", new AddIncomePanel(expenseTracker));
        addButton("Add Expense", new AddExpensePanel(expenseTracker));
        JButton viewincomebutton = new JButton("View Income");
        viewincomebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewIncome();
                showPanel(viewIncome());
            }
        });
        leftpanel.add(viewincomebutton);
        viewExpenseButton();
        removeExpenseButton();
        removeIncomeButton();
        viewCategoryButton();
        savingsButton();
        loadButton();
        quitButton();
    }

    //MODIFIES: this
    //EFFECTS: adds a new button with given text to the left panel and sets its associated panel display
    //on clicking the button
    public void addButton(String text, JPanel paneltoShow) {
        JButton button = new JButton(text);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(paneltoShow);
            }
        });
        leftpanel.add(button);
    }

    //MODIFIES: this
    //EFFECTS:replaces the current panel in the right panel with given panel
    public void showPanel(JPanel panel) {
        rightpanel.removeAll();
        rightpanel.add(panel, BorderLayout.CENTER);
        rightpanel.revalidate();
        rightpanel.repaint();
    }

    //getter
    public ExpenseTracker getExpenseTracker() {
        return expenseTracker;
    }

    //setter
    public void setExpenseTracker(ExpenseTracker expenseTracker) {
        this.expenseTracker = expenseTracker;
    }

    //MODIFIES: this
    //EFFECTS: add the button to view expenses in the left panel
    public void viewExpenseButton() {
        JButton viewExpenseButton = new JButton("View Expenses");
        viewExpenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewExpense();
                showPanel(viewExpense());
            }
        });
        leftpanel.add(viewExpenseButton);
    }

    //MODIFIES: this
    //EFFECTS: creates a button to load data in the left panel
    public void loadButton() {
        JButton loadDataButton = new JButton("Load Data");
        loadDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataFromFile();
            }
        });
        leftpanel.add(loadDataButton);
    }

    //MODIFIES: this
    //EFFECTS: loads the expenseTracker data from file into the gui
    public void loadDataFromFile() {
        expenseTracker.getIncomeList().clear();
        expenseTracker.getExpensesList().clear();

        try {
            jsonReader = new JsonReader(JSON_STORE);
            ExpenseTracker loadedTracker = jsonReader.read();
            ArrayList<Income> incomes = new ArrayList<>(expenseTracker.getIncomeList());
            ArrayList<Expenses> expensesArrayList = new ArrayList<>(expenseTracker.getExpensesList());

            for (Income income : loadedTracker.getIncomeList()) {
                if (!incomes.contains(income)) {
                    incomes.add(income);
                }
            }
            expenseTracker.getIncomeList().addAll(incomes);

            for (Expenses expense : loadedTracker.getExpensesList()) {
                if (!expensesArrayList.contains(expense)) {
                    expensesArrayList.add(expense);
                }
            }
            expenseTracker.getExpensesList().addAll(expensesArrayList);
            JOptionPane.showMessageDialog(this, "Data Loaded Successfully");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error: Unable to load data");
        }
    }

    //REQUIRES: expenseTracker must not be empty
    //EFFECTS: save the current Expense Tracker state to the file
    public void saveDataToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(expenseTracker);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Saved Successfully");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Unable to Write to File");
        }
    }

    //MODIFIES: this
    //EFFECTS: returns a JPanel displaying all the income in the expenseTracker with total income
    public JPanel viewIncome() {
        JTextArea incomeTextArea = new JTextArea();
        incomeTextArea.setEditable(false);
        StringBuilder incomedisplay = new StringBuilder();
        incomedisplay.append("Income:\n");

        double totalIncome = 0;
        for (Income income : expenseTracker.getIncomeList()) {
            incomedisplay.append("\nIncome ID: ").append(income.getIncomeID()).append("\n")
                    .append("Amount: $").append(income.getIncomeAmount()).append("\n")
                    .append("Description: ").append(income.getDescription()).append("\n\n");

            totalIncome += income.getIncomeAmount();
        }
        incomedisplay.append("\nTotal Income: $").append(String.format("%.2f", totalIncome)).append("\n\n");

        incomeTextArea.setText(incomedisplay.toString());
        JScrollPane scrollPane = new JScrollPane(incomeTextArea);
        JPanel incomePanel = new JPanel(new BorderLayout());
        incomePanel.add(scrollPane, BorderLayout.CENTER);

        ImageIcon viewincomeimg = new ImageIcon("data/search icon.png");
        viewincomeimg = new ImageIcon(viewincomeimg.getImage().getScaledInstance(180, 320, Image.SCALE_SMOOTH));
        JLabel imglabel = new JLabel(viewincomeimg);
        imglabel.setOpaque(true);
        imglabel.setBackground(Color.white);
        incomePanel.add(imglabel, BorderLayout.EAST);

        return incomePanel;
    }


    //MODIFIES: this
    //EFFECTS: returns a JPanel displaying all the expenses in the expenseTracker with total expense
    public JPanel viewExpense() {
        JTextArea expenseTextArea = new JTextArea();
        expenseTextArea.setEditable(false);
        StringBuilder expensedisplay = new StringBuilder();
        expensedisplay.append("Expenses:\n");

        double totalExpense = 0;
        for (Expenses expenses : expenseTracker.getExpensesList()) {
            expensedisplay.append("\n\nExpense ID: ").append(expenses.getId()).append("\n")
                    .append("Amount: $").append(expenses.getAmount()).append("\n").append("Date: ")
                    .append(expenses.getDate()).append("\n").append("Category: ").append(expenses.getCategory())
                    .append("\n").append("Description: ").append(expenses.getDecription()).append("\n");
            totalExpense += expenses.getAmount();
        }
        expensedisplay.append("\nTotal Expense: $").append(String.format("%.2f", totalExpense)).append("\n\n");

        expenseTextArea.setText(expensedisplay.toString());
        JScrollPane scrollPane = new JScrollPane(expenseTextArea);
        JPanel expensePanel = new JPanel(new BorderLayout());
        expensePanel.add(scrollPane, BorderLayout.CENTER);

        ImageIcon viewexpenseimg = new ImageIcon("data/viewExpense.png");
        Image scaledImage = viewexpenseimg.getImage().getScaledInstance(235, 600, Image.SCALE_SMOOTH);
        viewexpenseimg = new ImageIcon(scaledImage);
        JLabel imglabel = new JLabel(viewexpenseimg);
        expensePanel.add(imglabel, BorderLayout.EAST);
        return expensePanel;

    }

    //MODIFIES:this
    //EFFECTS:creates a quit button and quits the application while giving an option to save data to the file
    public void quitButton() {
        JButton quitbutton = new JButton("Quit");
        quitbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Do you want to save "
                                + "new data before quitting?", "Confirm  Exit", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.YES_OPTION) {
                    saveDataToFile();
                    System.exit(0);
                } else if (response == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }

            }
        });
        leftpanel.add(quitbutton);
    }

    //MODIFIES:this
    //EFFECTS: creates a remove expense button
    public void removeExpenseButton() {
        JButton removeExpense = new JButton("Remove Expense");
        removeExpense.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeExpense();
            }
        });
        leftpanel.add(removeExpense);
    }

    //MODIFIES:this,expenseslist
    //EFFECTS:removes an expense with the given ID from the expense Tracker else gives a message to enter valid ID
    public void removeExpense() {
        String expenseIDString = JOptionPane.showInputDialog("Enter the expense ID to remove: ");
        if (expenseIDString != null && !expenseIDString.trim().isEmpty()) {
            try {
                int expenseID = Integer.parseInt(expenseIDString);
                boolean found = false;
                for (int i = 0; i < expenseTracker.getExpensesList().size(); i++) {
                    if (expenseTracker.getExpensesList().get(i).getId() == expenseID) {
                        expenseTracker.getExpensesList().remove(i);
                        found = true;
                        break;
                    }
                }
                if (found) {
                    JOptionPane.showMessageDialog(this, "Expense Removed Successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Expense ID not found");
                }

            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(this, "Enter a valid ID");
            }


        }
    }

    //MODIFIES:this
    //EFFECTS: creates a remove income button
    public void removeIncomeButton() {
        JButton removeIncome = new JButton("Remove Income");
        removeIncome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeIncome();
            }
        });
        leftpanel.add(removeIncome);
    }

    //MODIFIES:this,incomelist
    //EFFECTS:removes an income with the given ID from the expense Tracker else gives a message to enter valid ID
    public void removeIncome() {
        String incomeIDString = JOptionPane.showInputDialog("Enter the Income ID to remove: ");
        if (incomeIDString != null && !incomeIDString.trim().isEmpty()) {
            try {
                int incomeID = Integer.parseInt(incomeIDString);
                boolean found = false;
                for (int i = 0; i < expenseTracker.getIncomeList().size(); i++) {
                    if (expenseTracker.getIncomeList().get(i).getIncomeID() == incomeID) {
                        expenseTracker.getIncomeList().remove(i);
                        found = true;
                        break;
                    }
                }
                if (found) {
                    JOptionPane.showMessageDialog(this, "Income Removed Successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Income ID not found");
                }

            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(this, "Enter a valid ID");
            }


        }
    }

    //MODIFIES:this
    //EFFECTS: creates a button to view savings(balance)
    public void savingsButton() {
        JButton savings = new JButton("View Balance");
        savings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewSavings();
            }
        });
        leftpanel.add(savings);

    }

    //MODIFIES: this
    //EFFECTS: displays savings by deducting the amount of entered expenses from the income amount
    public void viewSavings() {
        double totalExpenditure = 0;
        double totalIncome = 0;
        for (Expenses e : expenseTracker.getExpensesList()) {
            totalExpenditure += e.getAmount();
        }

        for (Income income : expenseTracker.getIncomeList()) {
            totalIncome += income.getIncomeAmount();
        }

        double savings = totalIncome - totalExpenditure;
        String formatSavings = String.format("%.2f", savings);
        JOptionPane.showMessageDialog(this, "The amount in your wallet after your "
                + "expenditure is: $ " + formatSavings, "Balance", JOptionPane.YES_OPTION);
    }


    //MODIFIES:this
    //EFFECTS: creates a view category wise expenses button with an action listener in the left panel
    public void viewCategoryButton() {
        JButton expensecategorybutton = new JButton("View Category Expenses");
        expensecategorybutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(viewCategorywisePanel());
            }
        });
        leftpanel.add(expensecategorybutton);
    }

    //MODIFIES: this
    //EFFECTS: returns a JPanel displaying all the expenses in the expenseTracker with total spending for a particular
    // user selected category
    public JPanel viewCategorywisePanel() {
        String category = JOptionPane.showInputDialog("Enter the Category to view expenses in: ");
        JTextArea categorytextArea = new JTextArea();
        categorytextArea.setEditable(false);

        StringBuilder categoryDisplay = new StringBuilder();
        categoryDisplay.append("Expenses for " + category + " Category: \n");

        double totalSpending = 0;
        for (Expenses expense : expenseTracker.getExpensesList()) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                categoryDisplay.append("\n\nExpense ID: ").append(expense.getId()).append("\n")
                        .append("Amount: $").append(expense.getAmount()).append("\n").append("Date: ")
                        .append(expense.getDate()).append("\n").append("Category: ").append(expense.getCategory())
                        .append("\n").append("Description: ").append(expense.getDecription()).append("\n");
                totalSpending += expense.getAmount();
            }
        }

        categoryDisplay.append("\nTotal Expense: $").append(String.format("%.2f", totalSpending)).append("\n\n");
        categorytextArea.setText(categoryDisplay.toString());
        JScrollPane scrollPane = new JScrollPane(categorytextArea);
        JPanel categorypanel = new JPanel(new BorderLayout());
        categorypanel.add(scrollPane,BorderLayout.CENTER);
        return  categorypanel;
    }


}
