package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTrackerTest {
    private ExpenseTracker testExpenseTracker;
    private Income income1;
    private Income income2;
    private Income income3;
    private Expenses expense1;
    private Expenses expense2;
    private Expenses expense3;

    @BeforeEach
    void runBefore() {
        testExpenseTracker = new ExpenseTracker();
        income1 = new Income(2, 2000, "testIncome");
        income2 = new Income(4, 4000, "testIncome2");
        income3 = new Income(3, 1000, "testIncome3");

        expense1 = new Expenses(1,100,"2023-07-19","trial","testdecription");
        expense2 = new Expenses(2, 300, "2023-08-21", "food", "Burger");
        expense3 = new Expenses(3, 400, "2023-10-02", "food", "pizza");
    }

    @Test
    void testConstructor() {
        assertEquals(0, testExpenseTracker.getIncomeList().size());
        assertEquals(0, testExpenseTracker.getExpensesList().size());
    }

    @Test
    void testAddIncome() {
        testExpenseTracker.addIncome(income1);

        assertEquals(1, testExpenseTracker.getNumberOfIncome());
        assertEquals(income1, testExpenseTracker.getIncomeList().get(0));
    }

    @Test
    void testAddMultipleIncome() {
        testExpenseTracker.addIncome(income1);
        assertEquals(1, testExpenseTracker.getNumberOfIncome());
        assertEquals(income1, testExpenseTracker.getIncomeList().get(0));


        testExpenseTracker.addIncome(income2);
        assertEquals(2, testExpenseTracker.getNumberOfIncome());
        assertEquals(income2, testExpenseTracker.getIncomeList().get(1));

        testExpenseTracker.addIncome(income3);
        assertEquals(3, testExpenseTracker.getNumberOfIncome());
        assertEquals(income3, testExpenseTracker.getIncomeList().get(2));
    }

    @Test
    void testAddExpenses() {
        testExpenseTracker.addExpenses(expense1);
        assertEquals(expense1, testExpenseTracker.getExpensesList().get(0));
        assertEquals(1, testExpenseTracker.getNumberOfExpenses());
    }

    @Test
    void testAddMultipleExpense() {
        testExpenseTracker.addExpenses(expense1);
        assertEquals(expense1, testExpenseTracker.getExpensesList().get(0));
        assertEquals(1, testExpenseTracker.getNumberOfExpenses());


        testExpenseTracker.addExpenses(expense2);
        assertEquals(expense2, testExpenseTracker.getExpensesList().get(1));
        assertEquals(2, testExpenseTracker.getNumberOfExpenses());


        testExpenseTracker.addExpenses(expense3);
        assertEquals(expense3, testExpenseTracker.getExpensesList().get(2));
        assertEquals(3, testExpenseTracker.getNumberOfExpenses());

    }

    @Test
    void testRemoveExpense() {
        testExpenseTracker.addExpenses(expense1);
        assertEquals(1, testExpenseTracker.getNumberOfExpenses());
        assertEquals(expense1, testExpenseTracker.getExpensesList().get(0));

        testExpenseTracker.addExpenses(expense2);
        assertEquals(2, testExpenseTracker.getNumberOfExpenses());
        assertEquals(expense2, testExpenseTracker.getExpensesList().get(1));

        testExpenseTracker.addExpenses(expense3);
        assertEquals(3, testExpenseTracker.getNumberOfExpenses());
        assertEquals(expense3, testExpenseTracker.getExpensesList().get(2));

        Boolean remove = testExpenseTracker.removeExpense(2);
        assertEquals(2, testExpenseTracker.getNumberOfExpenses());
        assertEquals(expense1, testExpenseTracker.getExpensesList().get(0));
        assertEquals(expense3, testExpenseTracker.getExpensesList().get(1));
        assertTrue(remove);
    }

    @Test
    void testRemoveMultipleExpense() {
        testExpenseTracker.addExpenses(expense1);
        assertEquals(1, testExpenseTracker.getNumberOfExpenses());
        assertEquals(expense1, testExpenseTracker.getExpensesList().get(0));

        testExpenseTracker.addExpenses(expense2);
        assertEquals(2, testExpenseTracker.getNumberOfExpenses());
        assertEquals(expense2, testExpenseTracker.getExpensesList().get(1));

        testExpenseTracker.addExpenses(expense3);
        assertEquals(3, testExpenseTracker.getNumberOfExpenses());
        assertEquals(expense3, testExpenseTracker.getExpensesList().get(2));

        Boolean remove1 = testExpenseTracker.removeExpense(2);
        assertEquals(2, testExpenseTracker.getNumberOfExpenses());
        assertEquals(expense1, testExpenseTracker.getExpensesList().get(0));
        assertEquals(expense3, testExpenseTracker.getExpensesList().get(1));
        assertTrue(remove1);

        Boolean remove2 = testExpenseTracker.removeExpense(1);
        assertEquals(1, testExpenseTracker.getNumberOfExpenses());
        assertEquals(expense3, testExpenseTracker.getExpensesList().get(0));
        assertTrue(remove2);


        Boolean remove3 = testExpenseTracker.removeExpense(2);
        assertEquals(1, testExpenseTracker.getNumberOfExpenses());
        assertEquals(expense3, testExpenseTracker.getExpensesList().get(0));
        assertFalse(remove3);

        Boolean remove4 = testExpenseTracker.removeExpense(4);
        assertEquals(1, testExpenseTracker.getNumberOfExpenses());
        assertEquals(expense3, testExpenseTracker.getExpensesList().get(0));
        assertFalse(remove4);
    }

    @Test
    void testRemoveIncome() {
        testExpenseTracker.addIncome(income1);
        assertEquals(1, testExpenseTracker.getNumberOfIncome());
        assertEquals(income1, testExpenseTracker.getIncomeList().get(0));

        testExpenseTracker.addIncome(income2);
        assertEquals(2, testExpenseTracker.getNumberOfIncome());
        assertEquals(income2, testExpenseTracker.getIncomeList().get(1));

        testExpenseTracker.addIncome(income3);
        assertEquals(3, testExpenseTracker.getNumberOfIncome());
        assertEquals(income3, testExpenseTracker.getIncomeList().get(2));

        Boolean remove = testExpenseTracker.removeIncome(4);
        assertEquals(2, testExpenseTracker.getNumberOfIncome());
        assertEquals(income1, testExpenseTracker.getIncomeList().get(0));
        assertEquals(income3, testExpenseTracker.getIncomeList().get(1));
        assertTrue(remove);
    }

    @Test
    void testRemoveMultipleIncome() {
        testExpenseTracker.addIncome(income1);
        assertEquals(1, testExpenseTracker.getNumberOfIncome());
        assertEquals(income1, testExpenseTracker.getIncomeList().get(0));

        testExpenseTracker.addIncome(income2);
        assertEquals(2, testExpenseTracker.getNumberOfIncome());
        assertEquals(income2, testExpenseTracker.getIncomeList().get(1));

        testExpenseTracker.addIncome(income3);
        assertEquals(3, testExpenseTracker.getNumberOfIncome());
        assertEquals(income3, testExpenseTracker.getIncomeList().get(2));

        Boolean remove1 = testExpenseTracker.removeIncome(2);
        assertEquals(2, testExpenseTracker.getNumberOfIncome());
        assertEquals(income2, testExpenseTracker.getIncomeList().get(0));
        assertEquals(income3, testExpenseTracker.getIncomeList().get(1));
        assertTrue(remove1);

        Boolean remove2 = testExpenseTracker.removeIncome(3);
        assertEquals(1, testExpenseTracker.getNumberOfIncome());
        assertEquals(income2, testExpenseTracker.getIncomeList().get(0));
        assertTrue(remove2);


        Boolean remove3 = testExpenseTracker.removeIncome(3);
        assertEquals(1, testExpenseTracker.getNumberOfIncome());
        assertEquals(income2, testExpenseTracker.getIncomeList().get(0));
        assertFalse(remove3);

        Boolean remove4 = testExpenseTracker.removeIncome(8);
        assertEquals(1, testExpenseTracker.getNumberOfIncome());
        assertEquals(income2, testExpenseTracker.getIncomeList().get(0));
        assertFalse(remove4);
    }










}
