package API;

import DataLayer.EmployeeDAOImpl;
import Entities.Employee;
import Services.EmployeeService;
import Services.EmployeeServiceImplemented;
import com.google.gson.Gson;
import exceptions.ResourceNotFound;
import io.javalin.Javalin;

public class WebApp {
    public static EmployeeService employeeService = new EmployeeServiceImplemented(new EmployeeDAOImpl());

    //Create a global Gson device that all WebApp methods use instead of locally creating it in each method
    public static Gson gson = new Gson();

    public static void main(String[] args) {
        Javalin app = Javalin.create();
        //Create
        app.post("/employees", context -> {
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

        //Read
        app.get("/employees/{id}", context -> {
            //id is equal to whatever number is put in the {id} search (i.e /findemployee/3 means id = 3
            int id = Integer.parseInt(context.pathParam("id"));

            try{
                String jsonEmployee = gson.toJson(employeeService.searchEmployeeByID(id));
                context.result(jsonEmployee);
            } catch (ResourceNotFound e) {
                context.status(404);
                context.result("No employee could be found with id of " + id);
            }

        });
        app.get("/employees", context -> {
            try{

                String json = gson.toJson(employeeService.searchAllEmployees());
                context.result(json);
            } catch (Exception e) {
                e.printStackTrace();
                context.status(404);
            }

        });


        app.start(8080);
    }
}
