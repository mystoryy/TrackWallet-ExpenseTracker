package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new ExpenseTrackerApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to continue:File not Found ");
        }

    }
}
