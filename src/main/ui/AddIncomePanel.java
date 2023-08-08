package ui;

import model.ExpenseTracker;
import model.Expenses;
import model.Income;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents the panel for adding income
public class AddIncomePanel extends JPanel {
    private JTextField amount;
    private JTextField descriptionfield;
    private ExpenseTracker expenseTracker;

    //EFFECTS: constructs an income panel with a given expense tracker and sets the layout and components
    public AddIncomePanel(ExpenseTracker expenseTracker) {
        this.expenseTracker = expenseTracker;
        setupLayout();
        setupComponents();
    }

    //MODIFIES: this
    //EFFECTS: sets the layout of the panel
    private void setupLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;

    }

    //MODIFIES: this
    //EFFECTS: sets the components including labels and text area for income details
    private void setupComponents() {
        JLabel amountLabel = new JLabel("Income Amount");
        amount = new JTextField(20);

        JLabel descriptionLabel = new JLabel("Description");
        descriptionfield = new JTextField(20);

        JButton addbutton = new JButton("Add Income");
        addbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addIncomegui();
            }
        });

        addLabelandComponent(amountLabel, amount, 1, 1);
        addLabelandComponent(descriptionLabel, descriptionfield, 2, 1);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(addbutton, gbc);

        addimage();


    }

    //MODIFIES: this
    //EFFECTS: adds an image to the add income Panel
    private void addimage() {
        ImageIcon addIncomeimg = new ImageIcon("data/addincome.png");
        Image scaledImage = addIncomeimg.getImage().getScaledInstance(200, 180, Image.SCALE_SMOOTH);
        addIncomeimg = new ImageIcon(scaledImage);
        JLabel imglabel = new JLabel(addIncomeimg);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = -3;
        gbc.gridy = 1;
        gbc.gridheight = 4;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(imglabel, gbc);
    }

    //EFFECTS: Adds a label and component to the panel at the specified grid position
    private void addLabelandComponent(JLabel label, JComponent component, int gridY, int gridX) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(label, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = gridX + 1;
        gbc.gridy = gridY;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(component, gbc);
    }

    //MODIFIES: this,incomeList
    //EFFECTS: Adds an income object to the income list in expense tracker with given details otherwise prompts user to
    //input valid details
    public void addIncomegui() {
        try {
            double incomeamt = Double.parseDouble(amount.getText());
            String description = descriptionfield.getText();
            if (!descriptionfield.getText().isEmpty()) {
                int nextIncomeId = 1;
                for (Income i : expenseTracker.getIncomeList()) {
                    if (i.getIncomeID() >= nextIncomeId) {
                        nextIncomeId = i.getIncomeID() + 1;
                    }
                }
                Income income = new Income(nextIncomeId, incomeamt, description);
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
