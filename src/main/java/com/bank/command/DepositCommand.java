package com.bank.command;

import com.bank.Account;
import com.bank.exception.MaximumLimitReachedException;
import com.bank.exception.NegativeFundsException;
import com.bank.persistence.FilePersistenceManager;

import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class DepositCommand implements ATMCommand{

    @Override
    public Account execute(HashMap<String, Account> registry, Scanner input, Account object) {
        ReentrantLock lock = object.getLock();
        while (true) {
            lock.lock();
            try {
                System.out.print("Enter the amount to deposit: ");
                String amount = input.next();

                if (object.Deposit(Double.parseDouble(amount))) {
                    System.out.println("Deposit successful: " + amount);
                    try {
                        FilePersistenceManager.saveData(registry);
                    } catch (IOException e) {
                        System.out.println("Unable to save new data!!");
                    }
                    break;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Wrong input, enter only numeric values!!");
            }
            catch (NegativeFundsException nfe) {
                System.out.println("Kindly Deposit money in positive value");
            }
            catch (MaximumLimitReachedException mlre) {
                System.out.println("Kindly Deposit money under per day maximum limit ^_^");
            }
            finally {
                lock.unlock();
            }
        }
        return object;
    }
}
