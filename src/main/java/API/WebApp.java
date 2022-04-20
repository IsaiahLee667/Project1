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
                context.status(200);
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
        //Update
        app.put("/employees/{id}", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            try{

                String body = context.body();
                Employee employee = gson.fromJson(body, Employee.class);
                //Turn the body from the postman app into a String and use it to create an employee


                employee.setId(id);
                //Set the id of the created employee to the id num from {id}
                employeeService.updateEmployee(employee);
                //update the employee with the new information
                context.status(201);
                context.result("Employee Updated");
            } catch (ResourceNotFound e) {
                context.status(404);
                context.result("No employee was found with that id to update");
            }

        });
        //Delete
        app.delete("/employees/{id}",context -> {

            int id = Integer.parseInt(context.pathParam("id"));

            try{
                employeeService.searchEmployeeByID(id);
                boolean result = employeeService.removeEmployeeById(id);
                if (result){
                    context.status(204);
                    //context.result("Employee was deleted");
                }
                else{
                    context.status(500);
                    context.result("Something has gone wrong, please try again");
                }
            } catch (ResourceNotFound e) {
                context.status(404);
                context.result("No employee was found with that id to delete");
            }


        });


        app.start(8080);
    }
}
