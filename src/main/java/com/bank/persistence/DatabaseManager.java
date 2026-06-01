package com.bank.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    // Database Connection Credentials URL
    // format -> jdbc:postgresql://host:port/database_name
    private static final String URL = "jdbc:postgresql://localhost:5432/bank_atm";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin123"; // If you didn't set a password yet, leave it empty ""

    /**
     * Factory method that creates and returns an active physical
     * Connection session to the bank_atm database.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}