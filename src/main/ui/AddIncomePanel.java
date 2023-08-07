package ui;

import model.ExpenseTracker;
import model.Income;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddIncomePanel extends JPanel {
    private JTextField amount;
    private JTextField descriptionfield;
    private ExpenseTracker expenseTracker;


    public AddIncomePanel(ExpenseTracker expenseTracker) {
        this.expenseTracker = expenseTracker;
        setupLayout();
        setupComponents();
//        expenseTracker = new ExpenseTracker("Test");

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
        JLabel amountLabel = new JLabel("Income Amount");
        amount = new JTextField(10);

        JLabel descriptionLabel = new JLabel("Description");
        descriptionfield = new JTextField(20);

        JButton addbutton = new JButton("Add Income");
        addbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addIncomegui();
            }
        });

        addLabelandComponent(amountLabel, amount, 1);
        addLabelandComponent(descriptionLabel, descriptionfield, 2);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
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


    public void addIncomegui() {
        try {
            double incomeamt = Double.parseDouble(amount.getText());
            String description = descriptionfield.getText();
            if (!descriptionfield.getText().isEmpty()) {
                Income income = new Income(expenseTracker.getNumberOfIncome() + 1, incomeamt, description);
                expenseTracker.addIncome(income);
                amount.setText("");
                descriptionfield.setText("");
                JOptionPane.showMessageDialog(this, "Income added successfully");

            } else {
                JOptionPane.showMessageDialog(this, "Please enter the description");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a correct income amount");

        }
    }

}
