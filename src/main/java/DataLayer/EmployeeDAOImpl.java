package DataLayer;

import Entities.Employee;

import Utilities.ConnectionUtil;

import java.sql.*;
import java.util.List;
import exceptions.ResourceNotFound;

import java.util.ArrayList;


public class EmployeeDAOImpl implements EmployeeDAO {


    @Override
    public Employee createEmployee(Employee employee) {
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "insert into employee values (default, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedId = rs.getInt("employee_id");
            employee.setId(generatedId);
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
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            //If nothing is returned
            //THIS CAUSES PROBLEMS IF MULTIPLE EMPLOYEES CAN BE FOUND UNDER THE SAME ID BUT FOR NOW THIS WORKS
            if(rs.next() == false){
                //Throw Error: Nothing was found
                throw new ResourceNotFound(id);
            }
            else{
                do{
                    Employee employee = new Employee();
                    employee.setId(rs.getInt("employee_id"));
                    employee.setFirstName(rs.getString("first_name"));
                    employee.setLastName(rs.getString("last_name"));
                    return employee;
                }while(rs.next());
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from employee";
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
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,employee.getFirstName());
            ps.setString(2,employee.getLastName());
            ps.setInt(3,employee.getId());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated == 0){
                throw new ResourceNotFound(employee.getId());
            }
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
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return  true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
