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
    public List<Expense> findExpensesByAnEmployee(int empId) {
        return null;
    }
}
