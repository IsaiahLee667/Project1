package Services;

import DataLayer.ExpenseDAO;
import DataLayer.ExpenseDAOImpl;
import Entities.Expense;

import java.util.List;

public class ExpenseServiceImpl implements  ExpenseService{
    private ExpenseDAO expenseDAO;
    public ExpenseServiceImpl(ExpenseDAO expenseDAO) {this.expenseDAO = expenseDAO;}
    @Override
    public Expense chargeExpense(Expense expense) {
        return this.expenseDAO.createExpense(expense);
    }

    @Override
    public Expense searchExpenseById(int id) {
        return this.expenseDAO.getExpenseById(id);
    }

    @Override
    public List<Expense> showAllExpenses() {
        return this.expenseDAO.getAllExpenses();
    }

    @Override
    public List<Expense> findExpensesByAnEmployee(int empId) {
        return this.expenseDAO.getExpensesByEmployeeId(empId);
    }

    @Override
    public List<Expense> findExpensesByStatus(String status) {
        return this.expenseDAO.getExpenseByStatus(status);
    }

    @Override
    public Expense reviseExpense(Expense expense) throws IllegalAccessException {
        if (expense.getStatus() == "Approved" || expense.getStatus() == "Denied"){
            throw new IllegalAccessException("You cannot edit a transaction that is already Approved or Denied");
        }
        return this.expenseDAO.updateExpense(expense);
    }

    @Override
    public Boolean removeExpenseById(int id) {
        return expenseDAO.deleteExpenseById(id);
    }
}
