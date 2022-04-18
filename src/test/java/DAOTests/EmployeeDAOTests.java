package DAOTests;

import DataLayer.EmployeeDAO;
import DataLayer.EmployeeDAOImpl;
import Entities.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeDAOTests {
    static EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Test
    void createEmployeeTest(){
        Employee John = new Employee(0,"John", "Lee");

        Employee JohnCreated = employeeDAO.createEmployee(John);
        System.out.println(JohnCreated);

        Assertions.assertNotEquals(0,JohnCreated.getId());
    }
}
