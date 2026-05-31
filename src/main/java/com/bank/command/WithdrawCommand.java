package com.bank.command;

import com.bank.Account;
import com.bank.exception.InsufficientFundsException;
import com.bank.exception.MaximumLimitReachedException;
import com.bank.exception.NegativeFundsException;
import com.bank.persistence.FilePersistenceManager;

import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class WithdrawCommand implements ATMCommand {
    @Override
    public Account execute(HashMap<String, Account> registry, Scanner input, Account object) {
        ReentrantLock lock = object.getLock();
        while(true){
            lock.lock();
            try {
                System.out.print("Enter the amount to withdraw: ");
                String amount = input.next();
                if(object.Withdraw(Double.parseDouble(amount))){
                    System.out.println("Withdraw successful: " + amount);
                    try {
                        FilePersistenceManager.saveData(registry);
                    } catch (IOException e) {
                        System.out.println("Unable to save new data!!");
                    }
                    break;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Wrong input, enter only numeric values!!");
            }
            catch (InsufficientFundsException ife) {
                System.out.println("Kindly Withdraw money under balance limit ^_^");
            }
            catch (NegativeFundsException nfe) {
                System.out.println("Kindly Withdraw money in positive value");
            }
            catch (MaximumLimitReachedException mlre) {
                System.out.println("Kindly Withdraw money under per day maximum limit ^_^");
            }
            finally {
                lock.unlock();
            }
        }

        return object;
    }
}
