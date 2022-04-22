package DAOTests;

import DataLayer.EmployeeDAO;
import DataLayer.EmployeeDAOImpl;
import Entities.Employee;
import java.util.List;
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

    @Test
    void findEmployeeById(){
        Employee foundEmployee = employeeDAO.getEmployeeById(3);
        System.out.println(foundEmployee.getFirstName());
        Assertions.assertEquals(3,foundEmployee.getId());
    }

    @Test
    void getAllEmployees(){
        List<Employee> allEmployees = employeeDAO.getAllEmployees();
        int numEmployees = allEmployees.size();
        System.out.println(allEmployees);
        Assertions.assertTrue(numEmployees > 2);
    }
    @Test
    void updateEmployeeById(){
        Employee foundEmployee = employeeDAO.getEmployeeById(3);
        foundEmployee.setFirstName("Miranda");
        employeeDAO.updateEmployee(foundEmployee);
        Employee updatedEmployee = employeeDAO.getEmployeeById(foundEmployee.getId());
        employeeDAO.updateEmployee(foundEmployee);
        Assertions.assertEquals("Miranda", updatedEmployee.getFirstName());
    }
}
