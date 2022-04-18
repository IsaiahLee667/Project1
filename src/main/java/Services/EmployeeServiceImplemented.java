package Services;

import DataLayer.EmployeeDAO;
import DataLayer.EmployeeDAOImpl;
import Entities.Employee;

public class EmployeeServiceImplemented implements EmployeeService{
    private EmployeeDAO employeeDAO;

    public EmployeeServiceImplemented(EmployeeDAO employeeDAO) {this.employeeDAO = employeeDAO;}


    @Override
    public Employee setupEmployee(Employee employee) {
        return this.employeeDAO.createEmployee(employee);
    }
}
