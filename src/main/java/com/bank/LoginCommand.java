package com.bank;

import java.util.Scanner;
import java.util.HashMap;

public class LoginCommand implements ATMCommand{
    @Override
    public Account execute(HashMap<String, Account> registry, Scanner input, Account object) {
        Account obj;
        System.out.print("Enter your name: ");
        String name = input.next();

        if(registry.containsKey(name)){
            obj = registry.get(name);
            return obj;
        }
        else {
            System.out.println("The user does not exist!!");
            return null;
        }
    }
}
