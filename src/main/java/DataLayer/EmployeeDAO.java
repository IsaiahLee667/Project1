package DataLayer;

import Entities.Employee;

public interface EmployeeDAO {

    Employee createEmployee (Employee employee);

    Employee getEmployeeById (int id);

    Employee updateEmployee (Employee employee);

    Boolean deleteEmployeeById(int id);

}
