package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseTest {

    private Expenses  testexpense;

    @BeforeEach
    void runBefore(){
        testexpense = new Expenses(1,100,"2023-07-19","trial","testdecription");

    }

    @Test
    void testConstructor(){
        assertEquals(1,testexpense.getId());
        assertEquals(100, testexpense.getAmount());
        assertEquals("trial", testexpense.getCategory());
        assertEquals("testdecription", testexpense.getDecription());
        assertEquals("2023-07-19", testexpense.getDate());
    }
}