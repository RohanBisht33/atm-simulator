package com.bank.command;

import com.bank.Account;
import com.bank.persistence.UserDAO;
import com.bank.security.SecurityService;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.HashMap;

public class LoginCommand implements ATMCommand {

    // Instantiate the Data Access Object layer
    private final UserDAO userDAO = new UserDAO();

    @Override
    public Account execute(HashMap<String, Account> registry, Scanner input, Account object) {
        System.out.println("Type exit or cancel to close menu ");
        System.out.print("Enter your userid: ");
        String userid = input.next();

        if (userid.equals("cancel") || userid.equals("exit")) {
            return null;
        }

        try {
            // Pull the profile directly from PostgreSQL on demand
            Account obj = userDAO.getUserById(userid);

            if (obj != null) {
                while (true) {
                    System.out.print("Enter your password: ");
                    String password = input.next();

                    if (password.equals("cancel") || password.equals("exit")) {
                        return null;
                    }

                    // Fix: Store the result in a temp variable so 'obj' stays safe!
                    Account authenticatedUser = SecurityService.unhashPassword(password, obj);

                    if (authenticatedUser != null) {
                        return authenticatedUser; // Login successful!
                    }

                    System.out.println("Incorrect password! Please try again.");
                }
            } else {
                System.out.println("The user does not exist!!");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred during validation: " + e.getMessage());
            return null;
        }
    }
}