package ui;

import model.ExpenseTracker;
import model.Expenses;
import model.Income;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents the panel for adding expense
public class AddExpensePanel extends JPanel {
    private JTextField expenseamount;
    private JTextField descriptionfield;
    private JTextField datefield;
    private JTextField categoryfield;
    private ExpenseTracker expenseTracker;


    //EFFECTS: constructs an expense panel with a given expense tracker and sets the layout and components
    public AddExpensePanel(ExpenseTracker expenseTracker) {
        this.expenseTracker = expenseTracker;
        setupLayout();
        setupComponents();
    }

    //EFFECTS: sets the layout of the panel
    private void setupLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
    }


    //EFFECTS: sets the components including labels and text area for expense details
    private void setupComponents() {
        JLabel amountLabel = new JLabel("Expense Amount");
        expenseamount = new JTextField(10);

        JLabel dateLabel = new JLabel("Date of Expenditure (dd-mm-yy)");
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
        addimage();

    }

    //EFFECTS: sets up the add expense button in the panel along with actionlistener to handle addition of expenses
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
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(addbutton, gbc);
    }


    //EFFECTS: Adds a label and component to the panel at the specified grid position
    private void addLabelandComponent(JLabel label, JComponent component, int gridY) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = gridY + 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(label, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = gridY + 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(component, gbc);
    }

    //MODIFIES: this
    //EFFECTS: adds an image to the add expense Panel
    private void addimage() {
        ImageIcon addexpenseeimg = new ImageIcon("data/addexpense.png");
        Image scaledImage = addexpenseeimg.getImage().getScaledInstance(200, 180, Image.SCALE_SMOOTH);
        addexpenseeimg = new ImageIcon(scaledImage);
        JLabel imglabel = new JLabel(addexpenseeimg);

        GridBagConstraints gbcimg = new GridBagConstraints();
        gbcimg.gridx = 0;
        gbcimg.gridy = 0;
        gbcimg.anchor = GridBagConstraints.PAGE_END;
        gbcimg.gridheight = 2;
        add(imglabel, gbcimg);
    }

    //MODIFIES: this,expenselist
    //EFFECTS: Adds an expense object to the expenses list in expense tracker with given details
    private void addExpensegui() {
        try {
            double expenseamt = Double.parseDouble(expenseamount.getText());
            String date = datefield.getText();
            String category = categoryfield.getText();
            String description = descriptionfield.getText();
            if (!categoryfield.getText().isEmpty()) {
                int nextExpenseId = 1;
                for (Expenses e : expenseTracker.getExpensesList()) {
                    if (e.getId() >= nextExpenseId) {
                        nextExpenseId = e.getId() + 1;
                    }
                }
                Expenses expense = new Expenses(nextExpenseId, expenseamt, date, category, description);
                expenseTracker.addExpenses(expense);
                setAddExpenseEmpty();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter Category");
            }

            JOptionPane.showMessageDialog(this, "Income added successfully");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a correct expense amount");

        }
    }

    //EFFECTS: clears all text fields in the panel and resets them to empty
    private void setAddExpenseEmpty() {
        expenseamount.setText("");
        datefield.setText("");
        categoryfield.setText("");
        descriptionfield.setText("");
    }
}
