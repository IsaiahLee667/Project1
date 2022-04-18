package Services;

import DataLayer.EmployeeDAO;
import Entities.Employee;

import java.util.List;

public class EmployeeServiceImplemented implements EmployeeService{
    private EmployeeDAO employeeDAO;

    public EmployeeServiceImplemented(EmployeeDAO employeeDAO) {this.employeeDAO = employeeDAO;}


    @Override
    public Employee setupEmployee(Employee employee) {
        return this.employeeDAO.createEmployee(employee);
    }

    @Override
    public Employee searchEmployeeByID(int id) {
        return this.employeeDAO.getEmployeeById(id);
    }

    @Override
    public List<Employee> searchAllEmployees() {
        return this.employeeDAO.getAllEmployees();
    }
}
