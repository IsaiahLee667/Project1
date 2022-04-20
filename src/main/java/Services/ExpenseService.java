package Services;

import Entities.Expense;

import java.util.List;

public interface ExpenseService {
    Expense chargeExpense (Expense expense);
    Expense searchExpenseById (int id);
    List<Expense> findExpensesByAnEmployee(int empId);
}
