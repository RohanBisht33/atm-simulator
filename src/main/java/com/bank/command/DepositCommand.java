package com.bank.command;

import com.bank.Account;
import com.bank.exception.MaximumLimitReachedException;
import com.bank.exception.NegativeFundsException;

import java.util.Scanner;
import java.util.HashMap;

public class DepositCommand implements ATMCommand{

    @Override
    public Account execute(HashMap<String, Account> registry, Scanner input, Account object) {
        System.out.print("Enter the amount to deposit: ");
        try {
            String amount = input.next();
            object.Deposit(Double.parseDouble(amount));
            System.out.println("Deposit successful: " + amount);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input, enter only numeric values!!");
        }
        catch (NegativeFundsException nfe) {
            System.out.println("Kindly Deposit money in positive value");
        }
        catch (MaximumLimitReachedException mlre) {
            System.out.println("Kindly Deposit money under per day maximum limit ^_^");
        }
        return object;
    }
}
