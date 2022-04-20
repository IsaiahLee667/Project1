package DAOTests;

import DataLayer.ExpenseDAO;
import DataLayer.ExpenseDAOImpl;
import Entities.Expense;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpenseDAOTests {
    static ExpenseDAO expenseDAO = new ExpenseDAOImpl();


    @Test
    void createEmployeeTest(){
        Expense expense = new Expense(0,5000,"Pending",System.currentTimeMillis(),1);
        Expense checkCreatedExpense = expenseDAO.createExpense(expense);
        System.out.println(checkCreatedExpense);
        Assertions.assertNotEquals(0,checkCreatedExpense.getId());


    }
}
