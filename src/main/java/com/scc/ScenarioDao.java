package com.scc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScenarioDao {

    // Insert new record using PreparedStatement
    public boolean addJobOrder(JobOrder order) {
        String sql = "INSERT INTO job_orders(customer_name, weight_kg, total_amount, status) VALUES(?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, order.getCustomerName());
            pstmt.setDouble(2, order.getWeightKg());
            pstmt.setDouble(3, order.getTotalAmount());
            pstmt.setString(4, order.getStatus());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
            return false;
        }
    }

    // Fetch all records from the database
    public List<JobOrder> getAllJobOrders() {
        List<JobOrder> orders = new ArrayList<>();
        String sql = "SELECT id, customer_name, weight_kg, total_amount, status FROM job_orders";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                JobOrder order = new JobOrder(
                        rs.getInt("id"),
                        rs.getString("customer_name"),
                        rs.getDouble("weight_kg"),
                        rs.getDouble("total_amount"),
                        rs.getString("status")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Query error: " + e.getMessage());
        }
        return orders;
    }
}
