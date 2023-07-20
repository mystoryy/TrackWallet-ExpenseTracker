package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncomeTest {
    private Income testIncome;

    @BeforeEach
    void runBefore() {
        testIncome = new Income(2, 2000, "testIncome");
    }

    @Test
    void testConstructor() {
        assertEquals(2, testIncome.getIncomeID());
        assertEquals(2000, testIncome.getIncomeAmount());
        assertEquals("testIncome", testIncome.getDescription());
    }

}
