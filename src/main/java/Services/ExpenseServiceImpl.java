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
        if (expense.getStatus().equals("Approved" )  || expense.getStatus().equals("Denied")){
            throw new IllegalAccessException();
        }
        return this.expenseDAO.updateExpense(expense);
    }

    @Override
    public Expense reviseExpenseStatus(int expenseId, String status) throws IllegalAccessException {
       Expense expense = this.expenseDAO.getExpenseById(expenseId);
        if (expense.getStatus().equals("Approved")  || expense.getStatus().equals("Denied")){
            throw new IllegalAccessException();
        }
        expense.setStatus(status);
        this.expenseDAO.updateExpense(expense);

        return expense;


    }

    @Override
    public Boolean removeExpenseById(int id) {
        return expenseDAO.deleteExpenseById(id);
    }
}
