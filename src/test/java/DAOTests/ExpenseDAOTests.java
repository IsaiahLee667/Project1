package DAOTests;

import DataLayer.ExpenseDAO;
import DataLayer.ExpenseDAOImpl;
import Entities.Expense;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.List;

public class ExpenseDAOTests {
    static ExpenseDAO expenseDAO = new ExpenseDAOImpl();


    @Test
    void createEmployeeTest(){
        Expense expense = new Expense(0,5000,"Pending",System.currentTimeMillis(),1);
        Expense checkCreatedExpense = expenseDAO.createExpense(expense);
        System.out.println(checkCreatedExpense);
        Assertions.assertNotEquals(0,checkCreatedExpense.getId());


    }
    @Test
    void findExpenseByIdTest(){
        Expense searchedExpense = expenseDAO.getExpenseById(2);
        SimpleDateFormat sdf = new SimpleDateFormat ("MMM dd,yyy HH:mm");
        System.out.println(sdf.format(searchedExpense.getPurchaseDate()));
        Assertions.assertEquals(5000, searchedExpense.getAmount());
    }
    @Test
    void findAllExpensesByEmployeeIdTest(){
        List<Expense> allExpenses = expenseDAO.getExpensesByEmployeeId(3);
        int numExpenses = allExpenses.size();
        System.out.println(allExpenses);
        System.out.println(numExpenses);
        Assertions.assertEquals(3,allExpenses.size());
    }

    @Test
    void findExpensesByStatusTest(){
        List<Expense> expenseByStatus = expenseDAO.getExpenseByStatus("Pending");
        System.out.println(expenseByStatus);

        Assertions.assertNotEquals(0,expenseByStatus);
    }
    @Test
    void grabAllExpensesTest(){
        List<Expense> allExpenses = expenseDAO.getAllExpenses();
        System.out.println(allExpenses);
    }
    @Test
    void updateExpenseTest(){
        Expense findExpense = expenseDAO.getExpenseById(4);
        findExpense.setAmount(35000);
        expenseDAO.updateExpense(findExpense);
        Expense updatedExpense = expenseDAO.getExpenseById(4);
        Assertions.assertEquals(35000,updatedExpense.getAmount());
    }
}
