package Entities;

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

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(long purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
