package com.bank.persistence;

import com.bank.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public void saveUser(Account account) throws SQLException {
        String insertUserSQL = "INSERT INTO users (user_id, name, password) VALUES (?, ?, ?)";
        String insertAccountSQL = "INSERT INTO accounts (user_id, balance) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.getConnection()) {
            // 1. Insert into the users authentication table
            try (PreparedStatement userStmt = conn.prepareStatement(insertUserSQL)) {
                userStmt.setString(1, account.getUserid());
                userStmt.setString(2, account.getName());
                userStmt.setString(3, account.getPassword());
                userStmt.executeUpdate();
            }

            // 2. Insert into the accounts financial tier table
            try (PreparedStatement accountStmt = conn.prepareStatement(insertAccountSQL)) {
                accountStmt.setString(1, account.getUserid());
                accountStmt.setDouble(2, account.getBalance());
                accountStmt.executeUpdate();
            }

            System.out.println("[Database] Registration complete: Profile and Balance persisted.");
        }
    }
    public Account getUserById(String userid) throws SQLException {
        String sql = "SELECT u.user_id, u.name, u.password, a.balance " +
                    "FROM users u " +
                    "JOIN accounts a ON u.user_id = a.user_id " +
                    "WHERE u.user_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userid);

            // The ResultSet captures the data grid returned by the server
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) { // Moves cursor to the first row found
                    String name = rs.getString("name");
                    String password = rs.getString("password");
                    double balance = rs.getDouble("balance");

                    // Reconstruct the domain object from database records!
                    return new Account(name, userid, password, balance);
                }
            }
        }
        return null; // Return null if no user matches that ID
    }
    public void updateBalance(String userid, double newBalance) throws SQLException {
        String sql = "UPDATE accounts SET balance = ? WHERE user_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 1. Set the new computational balance figure
            pstmt.setDouble(1, newBalance);

            // 2. Identify which specific user record to modify
            pstmt.setString(2, userid);

            pstmt.executeUpdate();
            System.out.println("[Database] Balance updated successfully in database matrix.");
        }
    }
}