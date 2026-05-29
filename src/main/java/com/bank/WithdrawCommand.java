package com.bank;

import java.util.Scanner;
import java.util.HashMap;

public class WithdrawCommand implements ATMCommand{

    @Override
    public Account execute(HashMap<String, Account> registry, Scanner input, Account object) {
        
        System.out.print("Enter the amount to withdraw: ");
        try {
            String amount = input.next();
            object.Withdraw(Double.parseDouble(amount));
            System.out.println("Withdraw successful: " + amount);
        } catch (NumberFormatException nfe) {
            System.out.println("Wrong input, enter only numeric values!!");
        }
        catch (InsufficientFundsException ife) {
            System.out.println("Kindly Withdraw money under balance limit ^_^");
        }
        return object;
    }
}
