package com.bank;

import java.util.Scanner;
import java.util.HashMap;

public class LogoutCommand implements ATMCommand {
    @Override
    public Account execute(HashMap<String, Account> registry, Scanner input, Account object) {
        System.out.println("You are logged out now! ");
        // Returning null tells Main.java that the active user session is officially over
        return null;
    }
}