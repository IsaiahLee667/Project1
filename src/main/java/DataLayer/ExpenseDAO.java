package DataLayer;

import Entities.Employee;
import Entities.Expense;

import java.util.List;

public interface ExpenseDAO {
    //Create
    Expense createExpense(Expense expense);
    //Read
    Expense getExpenseById(int id);

    List<Expense> getAllExpenses();

    List<Expense> getExpensesByEmployeeId(int id);

    List<Expense> getExpenseByStatus(String status);
    //Update
    Expense updateExpense(Expense expense);
    //Delete
    Boolean deleteExpenseById(int id);

    Boolean deleteExpenseByEmployeeId(int id);
}
