package com.bank.command;

import com.bank.Account;
import com.bank.exception.MinimumPasswordLengthException;
import com.bank.exception.NegativeFundsException;
import com.bank.persistence.UserDAO;
import com.bank.security.SecurityService;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.HashMap;

public class CreateCommand implements ATMCommand {

    @Override
    public Account execute(HashMap<String, Account> registry, Scanner input, Account object) {
        System.out.println("Type exit or cancel to close menu ");
        System.out.print("Enter your userid: ");
        String userid = input.next();

        if (userid.equals("cancel") || userid.equals("exit")) {
            return null;
        }

        UserDAO dao = new UserDAO();

        try {
            // Check the database to see if this userid is already taken
            Account existing = dao.getUserById(userid);

            if (existing == null) { // User does not exist, safe to create a new profile!
                Account obj;
                String password, name;

                while (true) {
                    try {
                        System.out.println("Create a password: ");
                        password = input.next();
                        if (password.equals("exit") || password.equals("cancel")) {
                            return null;
                        }
                        password = SecurityService.hashPassword(password);
                        System.out.println("Type your name: ");
                        name = input.next();
                        break;
                    } catch (MinimumPasswordLengthException mple) {
                        System.out.println("Password length is less than 8!!");
                    }
                }

                while (true) {
                    System.out.print("Enter the amount to deposit initially: ");
                    try {
                        String amount = input.next();
                        double balance = SecurityService.checkBalance(amount);

                        // Instantiate the domain object
                        obj = new Account(name, userid, password, balance);

                        // Persist across both users and accounts tables in PostgreSQL
                        dao.saveUser(obj);
                        System.out.println("Account creation successful");

                        return obj;
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong input, enter only numeric values!!");
                    } catch (NegativeFundsException nfe) {
                        System.out.println("Wrong input, enter only positive values!!");
                    } catch (SQLException sql) {
                        System.out.println("Unable to save data to the database matrix: " + sql.getMessage());
                        return null;
                    }
                }
            } else {
                System.out.println("The user already exists!!");
            }
        } catch (SQLException sqlException) {
            System.out.println("Database connection error during validation checking: " + sqlException.getMessage());
        }

        return null;
    }
}