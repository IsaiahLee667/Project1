package DataLayer;

import Entities.Employee;
import Entities.Expense;

public interface ExpenseDAO {
    //Create
    Expense createExpense(Expense expense);
    //Read
    Expense getExpenseById(int id);
    //Update
    Employee updateExpense(Expense expense);
    //Delete
    Boolean deleteExpenseById(int id);
}
