package com.scc;

public class JobOrder {
    private int id;
    private String customerName;
    private double weightKg;
    private double totalAmount;
    private String status;

    // Constructor with ID (used when fetching from database)
    public JobOrder(int id, String customerName, double weightKg, double totalAmount, String status) {
        this.id = id;
        this.customerName = customerName;
        this.weightKg = weightKg;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    // Constructor without ID (used when creating a new record before auto-increment)
    public JobOrder(String customerName, double weightKg, double totalAmount, String status) {
        this.customerName = customerName;
        this.weightKg = weightKg;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getId() { return id; }
    public String getCustomerName() { return customerName; }
    public double getWeightKg() { return weightKg; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
}
