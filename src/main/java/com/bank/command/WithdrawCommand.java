package com.bank.command;

import com.bank.Account;
import com.bank.exception.InsufficientFundsException;
import com.bank.exception.MaximumLimitReachedException;
import com.bank.exception.NegativeFundsException;
import com.bank.persistence.UserDAO;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class WithdrawCommand implements ATMCommand {
    @Override
    public Account execute(HashMap<String, Account> registry, Scanner input, Account object) {
        ReentrantLock lock = object.getLock();

        while (true) {
            // 1. Collect input OUTSIDE the lock
            System.out.print("Enter the amount to withdraw: ");
            String amount = input.next();

            // 2. Acquire lock only for the critical calculation
            lock.lock();
            try {
                if (object.Withdraw(Double.parseDouble(amount))) {
                    System.out.println("Withdraw successful: " + amount);
                    UserDAO dao = new UserDAO();
                    try {
                        dao.updateBalance(object.getUserid(), object.getBalance());
                    } catch (SQLException e) {
                        System.out.println("Critical: Could not synchronize financial records to disk!");
                    }
                    break;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Wrong input, enter only numeric values!!");
                break;
            }
            catch (InsufficientFundsException ife) {
                System.out.println("Kindly Withdraw money under balance limit ^_^");
                break;
            }
            catch (NegativeFundsException nfe) {
                System.out.println("Kindly Withdraw money in positive value");
                break;
            }
            catch (MaximumLimitReachedException mlre) {
                System.out.println("Kindly Withdraw money under per day maximum limit ^_^");
                break;
            }
            finally {
                lock.unlock();
            }
        }

        return object;
    }
}