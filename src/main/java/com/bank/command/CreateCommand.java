package com.bank.command;

import com.bank.Account;
import com.bank.exception.MinimumPasswordLengthException;
import com.bank.exception.NegativeFundsException;
import com.bank.persistence.FilePersistenceManager;
import com.bank.security.SecurityService;

import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

public class CreateCommand implements ATMCommand{

    @Override
    public Account execute(HashMap<String, Account> registry, Scanner input, Account object) {
        System.out.println("Type exit or cancel to close menu ");
        System.out.print("Enter your name: ");
        String name = input.next();
        if(name.equals("cancel") || name.equals("exit")){
            return null;
        }
        String password;
        if(!registry.containsKey(name)){
            Account obj;
            while (true) {
                try {
                    System.out.println("Create a password: ");
                    password = input.next();
                    if (password.equals("exit") || password.equals("cancel")){
                        return null;
                    }
                    password = SecurityService.hashPassword(password);
                    break;
                }
                catch (MinimumPasswordLengthException mple){
                    System.out.println("Password length is less than 8!!");
                }
            }
            while (true){
                System.out.print("Enter the amount to deposit initially: ");
                try {
                    String amount = input.next();
                    double balance = SecurityService.checkBalance(amount);
                    obj = new Account(name, password, balance);
                    registry.put(name, obj);
                    System.out.println("Account creation successful");
                    try {
                        FilePersistenceManager.saveData(registry);
                    } catch (IOException e) {
                        System.out.println("Unable to save new data!!");
                    }
                    return obj;
                }
                catch (NumberFormatException e) {
                    System.out.println("Wrong input, enter only numeric values!!");
                }
                catch (NegativeFundsException nfe){
                    System.out.println("Wrong input, enter only positive values!!");
                }
            }

        }
        else {
            System.out.println("The user already exists!!");
        }
        return null;
    }
}
