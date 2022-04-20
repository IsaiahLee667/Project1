package Entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;

//Data auto creates our getters and setters
@Data
public class Expense {
    private int id;
    private double amount;
    private String status;
    private long purchaseDate;
    private int employeeId;

    //amoprivate SimpleDateFormat sdf;

    public Expense() {
    }

    public Expense(int id, double amount,String status,long purchaseDate, int employeeId) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.purchaseDate = purchaseDate;
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }


}
