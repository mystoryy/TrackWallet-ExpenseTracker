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

public class ExpenseTrackerGUI extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private static final String JSON_STORE = "./data/ExpenseTracker.json";


    private ExpenseTracker expenseTracker;
    private JLabel label;
    private JPanel leftpanel;
    private JPanel rightpanel;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private ViewPanel viewPanel;
    private ViewIncomePanel incomePanel;


    //MODIFIES: this
    //EFFECTS: creates expense tracker GUI
    public ExpenseTrackerGUI() {
        super("TrackWallet - Expense Tracker");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        expenseTracker = new ExpenseTracker("Track Wallet");
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        incomePanel = new ViewIncomePanel(expenseTracker);

        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLayout(new BorderLayout());

        leftpanel = new JPanel();
        leftpanel.setLayout(new BoxLayout(leftpanel, BoxLayout.Y_AXIS));

        rightpanel = new JPanel();
        rightpanel.setLayout(new BorderLayout());

        addButtonPanel();
        add(leftpanel, BorderLayout.WEST);
        add(rightpanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void addButtonPanel() {
        leftpanel.setLayout(new GridLayout(8, 1));
//         AddIncomePanel addIncomePanel = new AddIncomePanel();
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

        JButton viewExpenseButton = new JButton("View Expenses");
        viewExpenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewExpense();
                showPanel(viewExpense());
            }
        });
        leftpanel.add(viewExpenseButton);

        loadButton();
        quitButton();


    }

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

    public void showPanel(JPanel panel) {
        rightpanel.removeAll();
        rightpanel.add(panel, BorderLayout.CENTER);
        rightpanel.revalidate();
        rightpanel.repaint();
    }


    public ExpenseTracker getExpenseTracker() {
        return expenseTracker;
    }

    public void setExpenseTracker(ExpenseTracker expenseTracker) {
        this.expenseTracker = expenseTracker;
    }


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
//            expenseTracker.getIncomeList().clear();

            for (Expenses expense : loadedTracker.getExpensesList()) {
                if (!expensesArrayList.contains(expense)) {
                    expensesArrayList.add(expense);
                }
//                expenseTracker.addExpenses(expense);
            }
            expenseTracker.getExpensesList().addAll(expensesArrayList);


            JOptionPane.showMessageDialog(this, "Data Loaded Successfully");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error: Unable to load data");
        }
    }

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
        return incomePanel;

    }

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
                    .append("\n").append("Description: ").append(expenses.getDecription()).append("\n\n");
            totalExpense += expenses.getAmount();
        }
        expensedisplay.append("\nTotal Expense: $").append(String.format("%.2f", totalExpense)).append("\n\n");

        expenseTextArea.setText(expensedisplay.toString());
        JScrollPane scrollPane = new JScrollPane(expenseTextArea);
        JPanel expensePanel = new JPanel(new BorderLayout());
        expensePanel.add(scrollPane, BorderLayout.CENTER);
        return expensePanel;

    }

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


}
