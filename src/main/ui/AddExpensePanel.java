package ui;

import model.ExpenseTracker;
import model.Expenses;
import model.Income;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddExpensePanel extends JPanel {
    private JTextField expenseamount;
    private JTextField descriptionfield;
    private JTextField datefield;
    private JTextField categoryfield;
    private ExpenseTracker expenseTracker;

    public AddExpensePanel(ExpenseTracker expenseTracker) {
        this.expenseTracker = expenseTracker;
        setupLayout();
        setupComponents();
//        expenseTracker = new ExpenseTracker("Expense Panel");

    }

    private void setupLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;


    }


    private void setupComponents() {
        JLabel amountLabel = new JLabel("Expense Amount");
        expenseamount = new JTextField(10);

        JLabel dateLabel = new JLabel("Date of Expenditure");
        datefield = new JTextField(20);

        JLabel categoryLabel = new JLabel("Category");
        categoryfield = new JTextField(30);

        JLabel descriptionLabel = new JLabel("Description");
        descriptionfield = new JTextField(40);

        addLabelandComponent(amountLabel, expenseamount, 1);
        addLabelandComponent(dateLabel, datefield, 2);
        addLabelandComponent(categoryLabel, categoryfield, 3);
        addLabelandComponent(descriptionLabel, descriptionfield, 4);

        setupAddButton();

    }

    private void setupAddButton() {

        JButton addbutton = new JButton("Add Expense");
        addbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addExpensegui();
            }
        });


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(addbutton, gbc);
    }


    private void addLabelandComponent(JLabel label, JComponent component, int gridY) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = gridY;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(label, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = gridY;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(component, gbc);
    }


    private void addExpensegui() {
        try {
            double expenseamt = Double.parseDouble(expenseamount.getText());
            String date = datefield.getText();
            String category = categoryfield.getText();
            String description = descriptionfield.getText();
            if (!categoryfield.getText().isEmpty()) {
                Expenses expense = new Expenses(expenseTracker.getNumberOfExpenses() + 1, expenseamt,
                        date, category, description);
                expenseTracker.addExpenses(expense);
                expenseamount.setText("");
                datefield.setText("");
                categoryfield.setText("");
                descriptionfield.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter Category");
            }

            JOptionPane.showMessageDialog(this, "Income added successfully");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a correct expense amount");

        }
    }
}
