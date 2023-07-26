package persistence;

import model.ExpenseTracker;
import model.Expenses;
import model.Income;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Modelled tests from JsonSerializationDemo repository given as resource for phase 2
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNOnExistentFile() {
        JsonReader reader = new JsonReader("./data/noFile.json\"");
        try {
            ExpenseTracker et = reader.read();
            fail("IOException expected");
        } catch (IOException e) {

        }

    }

    @Test
    void testReaderEmptyExpenseTracker() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            ExpenseTracker et = reader.read();
            assertEquals("My Expense Tracker", et.getName());
            assertEquals(0, et.getNumberOfExpenses());
            assertEquals(0, et.getNumberOfIncome());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            ExpenseTracker et = reader.read();
            assertEquals("My Expense Tracker", et.getName());
            List<Expenses> expenses = et.getExpensesList();
            List<Income> incomes = et.getIncomeList();
            assertEquals(2, expenses.size());
            assertEquals(2, incomes.size());
            checkExpense(20,50,"2023-07-30","food","test",
                    expenses.get(0));
            checkExpense(10,25,"2023-07-20","testcategory","testdescription",
                    expenses.get(1));
            checkIncome(8,4000,"testincome",incomes.get(0));
            checkIncome(5,1000,"rental",incomes.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}




