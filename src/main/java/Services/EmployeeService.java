package Services;

import Entities.Employee;
import java.util.List;


public interface EmployeeService {
    //Create
    Employee setupEmployee(Employee employee);
    //Read
    Employee searchEmployeeByID (int id);

    List<Employee> searchAllEmployees();

    //Update
    Employee updateEmployee(Employee employee);

    //Delete
    Boolean removeEmployeeById(int id);
}
