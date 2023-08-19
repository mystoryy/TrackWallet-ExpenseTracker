package persistence;

import model.Expenses;
import model.Income;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonTest {

    protected void checkIncome(int incomeId, double income, String description, Income testincome) {
        assertEquals(incomeId, testincome.getIncomeID());
        assertEquals(income, testincome.getIncomeAmount());
        assertEquals(description, testincome.getDescription());

    }

    protected void checkExpense(int expenseId, double amount, String date, String category, String description,
                                Expenses testexpense) {
        assertEquals(expenseId, testexpense.getId());
        assertEquals(amount, testexpense.getAmount());
        assertEquals(date, testexpense.getDate());
        assertEquals(category, testexpense.getCategory());
        assertEquals(description, testexpense.getDecription());

    }


}
