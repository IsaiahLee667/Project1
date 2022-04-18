package API;

import DataLayer.EmployeeDAOImpl;
import Entities.Employee;
import Services.EmployeeService;
import Services.EmployeeServiceImplemented;
import com.google.gson.Gson;
import io.javalin.Javalin;

public class WebApp {
    public static EmployeeService employeeService = new EmployeeServiceImplemented(new EmployeeDAOImpl());

    //Create a global Gson device that all WebApp methods use instead of locally creating it in each method
    public static Gson gson = new Gson();

    public static void main(String[] args) {
        Javalin app = Javalin.create();

        app.post("/createEmployee", context -> {
            String emp = context.body();
            //String EMP is the message we post from Postman
            Employee employee = gson.fromJson(emp, Employee.class);
            //Create an Employee from the emp message using the employee.class fields as a template
            employeeService.setupEmployee(employee);
            //Create an employee in our Employee Services from this created employee
            context.status(201);
            String employeeToJson = gson.toJson(employee);
            //Return the result back to Postman (nothing should change except for the ID which goes from 0 to its serial value)
            context.result(employeeToJson);
        });
        app.start(8080);
    }
}
