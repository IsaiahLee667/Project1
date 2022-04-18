package Entities;

import lombok.Data;

//Data auto creates our getters and setters
@Data
public class Expense {
    private int id;
    private double amount;
    private String status;
    private long purchaseDate;
    private int employeeId;

    public Expense() {
    }

    public Expense(int id, double amount, long purchaseDate, int employeeId) {
        this.id = id;
        this.amount = amount;
        this.purchaseDate = purchaseDate;
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }


}
