package Services;

import Entities.Employee;


public interface EmployeeService {
    Employee setupEmployee(Employee employee);

    Employee searchEmployeeByID (int id);
}
