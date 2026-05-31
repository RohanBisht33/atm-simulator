package com.bank.command;

import com.bank.Account;
import com.bank.security.SecurityService;

import java.util.Scanner;
import java.util.HashMap;

public class LoginCommand implements ATMCommand{
    @Override
    public Account execute(HashMap<String, Account> registry, Scanner input, Account object) {
        Account obj;
        System.out.println("Type exit or cancel to close menu ");
        System.out.print("Enter your name: ");
        String name = input.next();

        if(name.equals("cancel") || name.equals("exit")){
            return null;
        }
        else if(registry.containsKey(name)){
            while(true){
                obj = registry.get(name);
                System.out.print("Enter your password: ");
                String password = input.next();
                if(password.equals("cancel") || password.equals("exit")){
                    return null;
                }
                obj = SecurityService.unhashPassword(password, obj);
                if (obj != null) {
                    return obj;
                }
            }
        }
        else {
            System.out.println("The user does not exist!!");
            return null;
        }
    }
}
