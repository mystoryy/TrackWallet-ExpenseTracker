# My Personal Project

## TrackWallet - Expense Tracker

The expense tracker application helps in managing personal finances
by tracking and categorizing the expenses.It provides insights into the financial health of the user
.The application allows users to track their expenses,
set budgets and view spending based on the time and category.This application gives clear understanding of 
one's spending patterns,identifying areas to save money and ultimately work towards understanding financial goals.

The application is designed for individuals looking  to gain better control 
over their personal finances especially like frequent shoppers.

I recognize that responsible money management and prioritizing expenses  is important 
and believe that this application would align with that  by helping individuals 
make informed financial decisions and contribute to stability.

### *User Stories*:
- As a user, I want to be able to **add** income to my account
- As a user, I want to be able to **add** expenses to my account along with details like date,amount,category and description.
- As a user, I want to be able to view the list of income and expenses
- As a user, I want to be able to remove an income and expenses from my account
- As a user, I want to be able to see the savings of my account 
- As a user, I want to be able to select a category and see the expenses belonging to it
- As a user, I want to be able to save my expense and income list when I select the save option
- As a user, I want to be able to load my expense and income list when I select the load data option


## Instructions for Grader
- You can generate the first required action related to adding Xs to a Y by clicking the "Add Income" button on the left
  side of the application window.Fill in the details in the fields and  click add income below to complete.
- You can also generate the action related to adding Xs to Y by clicking "Add Expense" button on the left side.
- You can generate the second required action related to adding Xs to Y by clicking on "View Category Expenses" button.
 You can enter a category for which you can see all the expenses with the same category that were added to the list.
- You can locate my visual component as soon as the application is run.An image is displayed beside the title in starting
 panel.You can also find more images displayed in all the four add income/expense,view income/expense buttons when they are clicked
- You can save the state of my application by clicking on "Quit"
 button and then selecting "yes" option in the pop-up window.
- You can reload the state of my application by clicking on the "Load" button on the left side.

- You can also generate another action related to adding Xs to Y by clicking on "Remove Income/Expense" buttons where you can 
input an expense ID/income ID to delete the corresponding entry from the list
- You can also view the balance amount remaining by clicking "View Balance" which shows the balance money in a pop-up window


## Phase 4:Task 2

A representative sample of events that occur when my program is run is as follows:<br>

Tue Aug 08 18:17:10 PDT 2023 <br>
An Income Added: ID- 1 with description- salary

Tue Aug 08 18:17:22 PDT 2023<br>
An Income Added: ID- 2 with description- tax credits

Tue Aug 08 18:17:34 PDT 2023<br>
An Income Added: ID- 3 with description- business

Tue Aug 08 18:17:54 PDT 2023<br>
An Expense Added: ID- 1 in bills category

Tue Aug 08 18:18:11 PDT 2023<br>
An Expense Added: ID- 2 in food category

Tue Aug 08 18:18:30 PDT 2023<br>
An Expense Added: ID- 3 in food category

Tue Aug 08 18:19:04 PDT 2023<br>
An Expense Added: ID- 4 in bills category

Tue Aug 08 18:19:19 PDT 2023<br>
An Income with ID: 3 removed

Tue Aug 08 18:19:30 PDT 2023<br>
An Expense with ID: 4 removed


## Phase 4: Task 3
If I had more time to work on the project,I would implement singleton design pattern for ExpenseTracker class so that only 
one object which is accessible everywhere is created over the entire application.This would be a better design structure instead 
of creating multiple objects for each class associated with ExpenseTracker class.

I would also refactor the ExpenseTrackerGUI class such that it follows the single responsibility principle and doesn't have 
numerous responsibilities.In order to do so,I would create  new classes for each function like reading,writing and manipulating the data.
I would also reduce duplicated code by refactoring into methods,thus improving coupling.

Furthermore,I would also like to apply observer design pattern,so that different parts of the application can be notified of the change in state like
changes in income and expenses list.Various components that depend on this data could be automatically notified and updated.

