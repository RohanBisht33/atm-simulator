package com.bank.command;

import com.bank.Account;
import com.bank.exception.MaximumLimitReachedException;
import com.bank.exception.NegativeFundsException;
import com.bank.persistence.UserDAO;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class DepositCommand implements ATMCommand {

    @Override
    public Account execute(HashMap<String, Account> registry, Scanner input, Account object) {
        ReentrantLock lock = object.getLock();

        while (true) {
            // 1. Collect input OUTSIDE the lock so other threads aren't blocked
            System.out.print("Enter the amount to deposit: ");
            String amount = input.next();

            // 2. Acquire lock only for the critical state modification
            lock.lock();
            try {
                if (object.Deposit(Double.parseDouble(amount))) {
                    System.out.println("Deposit successful: " + amount);
                    UserDAO dao = new UserDAO();
                    try {
                        dao.updateBalance(object.getUserid(), object.getBalance());
                    } catch (SQLException e) {
                        System.out.println("Critical: Could not synchronize financial records to disk!");
                    }
                    break; // Exit loop on success
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Wrong input, enter only numeric values!!");
                break; // Break lock block to prompt safely again
            }
            catch (NegativeFundsException nfe) {
                System.out.println("Kindly Deposit money in positive value");
                break;
            }
            catch (MaximumLimitReachedException mlre) {
                System.out.println("Kindly Deposit money under per day maximum limit ^_^");
                break;
            }
            finally {
                lock.unlock(); // Ensure lock is released safely
            }
        }
        return object;
    }
}