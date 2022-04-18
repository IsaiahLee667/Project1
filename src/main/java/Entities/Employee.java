package Entities;

import lombok.Data;

@Data
public class Employee {
    private int id;
    private String firstName;
    private String lastName;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }


}
