package DataLayer;

import Entities.Employee;

import Utilities.ConnectionUtil;

import java.sql.*;
import java.util.List;

import Utilities.LogLevel;
import Utilities.Logger;
import exceptions.ResourceNotFound;

import java.util.ArrayList;


public class EmployeeDAOImpl implements EmployeeDAO {
    public Logger logger = new Logger();

    @Override
    public Employee createEmployee(Employee employee) {
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "insert into employee values (default, ?, ?)";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedId = rs.getInt("employee_id");
            employee.setId(generatedId);
            logger.log("Employee Created", LogLevel.INFO);
            return employee;

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from employee where employee_id = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            //If nothing is returned
            //THIS CAUSES PROBLEMS IF MULTIPLE EMPLOYEES CAN BE FOUND UNDER THE SAME ID BUT FOR NOW THIS WORKS
            if(rs.next() == false){
                //Throw Error: Nothing was found
                logger.log("Nothing found in the search by id", LogLevel.ERROR);
                throw new ResourceNotFound(id);
            }
            else{
                do{
                    Employee employee = new Employee();
                    employee.setId(rs.getInt("employee_id"));
                    employee.setFirstName(rs.getString("first_name"));
                    employee.setLastName(rs.getString("last_name"));
                    logger.log("Returned employee by Id", LogLevel.INFO);
                    return employee;
                }while(rs.next());
            }


        } catch (SQLException e) {
            logger.log("Something went wrong while trying to run the SQL method in getEmployeeById", LogLevel.ERROR);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from employee";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            ArrayList allEmployees = new ArrayList();
            while(rs.next()){
                Employee employee = new Employee();
                employee.setId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                allEmployees.add(employee);
            }
            logger.log("All employees returned", LogLevel.INFO);
            return allEmployees;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "update employee set first_name = ?, last_name = ? where employee_id = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,employee.getFirstName());
            ps.setString(2,employee.getLastName());
            ps.setInt(3,employee.getId());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated == 0){
                throw new ResourceNotFound(employee.getId());
            }
            logger.log("Employee Updated", LogLevel.INFO);
            return employee;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteEmployeeById(int id) {
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "delete from employee where employee_id = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            logger.log("Employee Deleted", LogLevel.DEBUG);
            return  true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
