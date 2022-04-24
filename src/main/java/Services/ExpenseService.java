package Services;

import Entities.Expense;

import java.util.List;

public interface ExpenseService {
    //create
    Expense chargeExpense (Expense expense);
    //read
    Expense searchExpenseById (int id);

    List<Expense> showAllExpenses();

    List<Expense> findExpensesByAnEmployee(int empId);

    List<Expense> findExpensesByStatus (String status);

    //update
    Expense reviseExpense (Expense expense) throws IllegalAccessException;

    Expense reviseExpenseStatus(int expenseId, String status) throws IllegalAccessException;

    //Delete
    Boolean removeExpenseById(int id);
}
