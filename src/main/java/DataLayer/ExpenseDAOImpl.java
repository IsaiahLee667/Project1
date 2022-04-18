package DataLayer;

import Entities.Employee;
import Entities.Expense;

public class ExpenseDAOImpl implements ExpenseDAO{
    @Override
    public Expense createExpense(Expense expense) {
        try{
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense getExpenseById(int id) {
        return null;
    }

    @Override
    public Employee updateExpense(Expense expense) {
        return null;
    }

    @Override
    public Boolean deleteExpenseById(int id) {
        return null;
    }
}
