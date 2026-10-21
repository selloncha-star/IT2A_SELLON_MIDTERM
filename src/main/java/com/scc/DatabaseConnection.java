package com.scc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:laundry_shop.db";

    static {
        // This suppresses the SLF4J and SQLite native loading warning messages from showing in the console
        Logger.getLogger("org.sqlite").setLevel(Level.OFF);
        System.setProperty("slf4j.internal.verbosity", "WARN");
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            createTable(conn);
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
        return conn;
    }

    private static void createTable(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS job_orders (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "customer_name TEXT NOT NULL, " +
                "weight_kg REAL NOT NULL, " +
                "total_amount REAL NOT NULL, " +
                "status TEXT NOT NULL);";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Table creation error: " + e.getMessage());
        }
    }
}