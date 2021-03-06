package API;

import DataLayer.EmployeeDAOImpl;
import DataLayer.ExpenseDAOImpl;
import Entities.Employee;
import Entities.Expense;
import Services.EmployeeService;
import Services.EmployeeServiceImplemented;
import Services.ExpenseService;
import Services.ExpenseServiceImpl;
import com.google.gson.Gson;
import exceptions.ResourceNotFound;
import io.javalin.Javalin;

import java.util.List;

public class WebApp {
    public static EmployeeService employeeService = new EmployeeServiceImplemented(new EmployeeDAOImpl());
    public static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDAOImpl());

    //Create a global Gson device that all WebApp methods use instead of locally creating it in each method
    public static Gson gson = new Gson();

    public static void main(String[] args) {
        Javalin app = Javalin.create();
        //Blankslate
        app.get("/", context -> {

            context.result("Default message from a blank page");
        });




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
                List<Expense> expenses = expenseService.findExpensesByAnEmployee(id);
                if (expenses.isEmpty() != true){
                    throw new IllegalAccessException();

                }




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
            }catch(IllegalAccessException e){
                context.status(400);
                context.result("There are existing expenses for that employee, cannot delete this employee");
            }


        });
////EXPENSE FUNCTIONS START HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        //Create
        app.post("/expenses", context -> {
            String emp = context.body();
            //String EMP is the message we post from Postman
            try{
                Expense expense = gson.fromJson(emp, Expense.class);
                if (expense.getAmount() <= 0){
                    throw new ArithmeticException();
                }

                expense.setPurchaseDate(System.currentTimeMillis());
                //Create an Expense using our Expense entity as a template
                //Set the date value to current time in milliseconds
                expenseService.chargeExpense(expense);
                //Create an employee in our Employee Services from this created employee
                context.status(201);
                String expenseToJson = gson.toJson(expense);
                //Return the result back to Postman (nothing should change except for the ID which goes from 0 to its serial value)
                context.result(expenseToJson);
            } catch (ArithmeticException e) {
                context.status(401);
                context.result("Amount must be greater than 0");
            }

            //Add Expense to Exployee with given Id


        });

        app.post("/employees/{id}/expenses", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            if (id == 0){
                throw new ResourceNotFound(id);
            }
            try{
                employeeService.searchEmployeeByID(id);
                String expenseToAdd = context.body();
                Expense expense = gson.fromJson(expenseToAdd, Expense.class);
                expense.setEmployeeId(id);
                expenseService.chargeExpense(expense);
                context.status(201);
                context.result("Expense was added to employee with id of " + id);
            } catch (ResourceNotFound e) {
                context.status(404);
                context.result("No employee could be found with that id to delete");
            }


        });


        //Read
        //Get expense by EXPENSE ID (i.e get one expense)
        app.get("/expenses/{id}", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            try{
                    String jsonExpense = gson.toJson(expenseService.searchExpenseById(id));
                    context.status(200);
                    context.result(jsonExpense);}
            catch (ResourceNotFound e) {
                    context.status(404);
                    context.result("No expense could be found with an id of " + id);
                }

        });
        //Get All Expenses under 1 employee ID
        app.get("/employees/{id}/expenses", context -> {
            int id = Integer.parseInt(context.pathParam("id"));

            try{
                String JsonAllEmpExpenses = gson.toJson(expenseService.findExpensesByAnEmployee(id));
                context.status(201);
                context.result(JsonAllEmpExpenses);
            } catch (ResourceNotFound e) {
                context.status(404);
                context.result("No expense could be found for the employee with an id of " + id);
            }

        });
        //Get all Expenses AND Get all Expenses by Status
        app.get("/expenses",context -> {
            //int id = Integer.parseInt(context.pathParam("id"));
            String searchStatus = context.queryParam("status");
            if (searchStatus == null){
                List<Expense> expenses = expenseService.showAllExpenses();
                String jsonAllExpenses = gson.toJson(expenses);
                context.result(jsonAllExpenses);
                context.status(202);
            }
            else{
                try{
                    String jsonAllExpensesByStatus = gson.toJson(expenseService.findExpensesByStatus(searchStatus));
                    context.status(201);
                    context.result(jsonAllExpensesByStatus);
                } catch (ResourceNotFound e) {

                    context.status(404);
                    context.result("No status of " + searchStatus + " exists");

                }
            }


        });
        //Update
        app.put("/expenses/{id}", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            try{
                String body = context.body();
                //Expense timeexpense = expenseService.searchExpenseById(id);
                Expense expense = gson.fromJson(body, Expense.class);
               // expense.setPurchaseDate(timeexpense.getPurchaseDate());
                if (expense.getId() != id){
                    throw new IllegalArgumentException();
                }

                expense.setId(id);
                expense.setPurchaseDate(System.currentTimeMillis());
                expenseService.reviseExpense(expense);
                context.status(201);
                context.result("Expense Updated");
            } catch (ResourceNotFound e) {
                context.status(404);
                context.result("No employee was found with that id to update");
            }catch (IllegalAccessException e){
                context.status(403);
                context.result("You cannot edit a transaction status to Approved or Denied");
            }
            catch (IllegalArgumentException e){
                context.status(403);
                context.result("Id in the Path Param and Id in the JSON body must be the same");
            }


        });

        app.patch("/expenses/{id}/{status}", context -> {
            String status = context.pathParam("status");
            int id = Integer.parseInt(context.pathParam("id"));

            try{

                if (status.equalsIgnoreCase("Approve")) status = "Approved";
                else if (status.equalsIgnoreCase("Deny")) status = "Denied";
                else throw new IllegalArgumentException("Status must either be Approve or Deny");

                Expense newExpense = expenseService.reviseExpenseStatus(id,status);
                String changedExpense = gson.toJson(newExpense);

                context.status(201);
                context.result(changedExpense);
            } catch (IllegalArgumentException e) {
                context.status(404);
                context.result(String.valueOf(e));
            }catch (ResourceNotFound e){
                context.status(404);
            }catch (IllegalAccessException e){
                context.status(404);
                context.result("Status is already Approved or Denied, and cannot be changed");
            }



        });





        //Delete
        app.delete("/expenses/{id}",context -> {

            int id = Integer.parseInt(context.pathParam("id"));

            try{
                expenseService.searchExpenseById(id);
                boolean result = expenseService.removeExpenseById(id);
                if (result){
                    context.status(204);
                    context.result("Employee was deleted");
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

        //app.start(5000);
        app.start(8080);
    }
}
