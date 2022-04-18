package DataLayer;

import Entities.Employee;

import java.util.List;


public interface EmployeeDAO {

    Employee createEmployee (Employee employee);

    Employee getEmployeeById (int id);

    List<Employee> getAllEmployees();

    Employee updateEmployee (Employee employee);

    Boolean deleteEmployeeById(int id);

}
