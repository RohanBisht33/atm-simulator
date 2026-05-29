package com.bank;

import java.util.Scanner;
import java.util.HashMap;

public class CreateCommand implements ATMCommand{

    @Override
    public Account execute(HashMap<String, Account> registry, Scanner input, Account object) {
        Account obj;
        System.out.print("Enter your name: ");
        String name = input.next();

        if(!registry.containsKey(name)){
            System.out.print("Enter the amount to deposit initially: ");
            try {
                String amount = input.next();
                obj = new Account(name, Double.parseDouble(amount));
                registry.put(name, obj);
                System.out.println("Account creation successful");
                return obj;

            } catch (NumberFormatException e) {
                System.out.println("Wrong input, enter only numeric values!!");
            }
        }
        else {
            System.out.println("The user already exists!!");
        }
        return null;
    }
}
