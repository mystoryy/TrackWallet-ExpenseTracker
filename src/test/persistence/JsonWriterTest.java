package persistence;

import model.ExpenseTracker;
import model.Expenses;
import model.Income;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            ExpenseTracker et = new ExpenseTracker("My Expense Tracker");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ExpenseTracker et = new  ExpenseTracker("My Expense Tracker");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(et);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            et = reader.read();
            assertEquals("My Expense Tracker", et.getName());
            assertEquals(0, et.getNumberOfExpenses());
            assertEquals(0, et.getNumberOfIncome());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ExpenseTracker et = new  ExpenseTracker("My Expense Tracker");
            et.addExpenses(new Expenses(10,25,"2023-07-20","testcategory",
                    "testdescription"));
            et.addExpenses(new Expenses(20,50,"2023-07-30","food",
                    "test"));

            et.addIncome(new Income(5,1000,"rental"));
            et.addIncome(new Income(8,4000,"testincome"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(et);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            et = reader.read();
            assertEquals("My Expense Tracker", et.getName());
            List<Expenses> expenses = et.getExpensesList();
            assertEquals(2, expenses.size());
            List<Income> incomes = et.getIncomeList();
            assertEquals(2,incomes.size());
            checkExpense(10,25,"2023-07-20","testcategory","testdescription",
                    expenses.get(0));
            checkExpense(20,50,"2023-07-30","food","test",
                    expenses.get(1));
            checkIncome(5,1000,"rental",incomes.get(0));
            checkIncome(8,4000,"testincome",incomes.get(1));



        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
