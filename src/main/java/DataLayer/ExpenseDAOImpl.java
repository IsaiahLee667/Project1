package DataLayer;

import Entities.Employee;
import Entities.Expense;
import Utilities.ConnectionUtil;
import exceptions.ResourceNotFound;

import java.sql.*;
import java.util.List;

public class ExpenseDAOImpl implements ExpenseDAO{
    @Override
    public Expense createExpense(Expense expense) {
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "insert into expense values (default, ?, ?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, expense.getAmount());
            ps.setString(2, expense.getStatus());
            ps.setLong(3, expense.getPurchaseDate());
            ps.setInt(4, expense.getEmployeeId());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedId = rs.getInt("expense_id");
            expense.setId(generatedId);
            return expense;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense getExpenseById(int id) {
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from expense where expense_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if(rs.next() == false){
                //Throw Error: Nothing was found
                throw new ResourceNotFound(id);
            }
            else{
                do{
                    Expense expense = new Expense();
                    expense.setId(rs.getInt("expense_id"));
                    expense.setAmount(rs.getDouble("expense_amount"));
                    expense.setStatus(rs.getString("status"));
                    expense.setPurchaseDate(rs.getLong("date_of_purchase"));
                    expense.setEmployeeId(rs.getInt("emp_id"));
                    return expense;
                }while(rs.next());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Expense> getExpensesByEmployeeId(int id) {
        return null;
    }

    @Override
    public Employee updateExpense(Expense expense) {
        try{
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteExpenseById(int id) {
        try{
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
