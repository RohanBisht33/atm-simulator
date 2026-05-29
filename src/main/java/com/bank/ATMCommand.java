package com.bank;
import java.util.Scanner;
import java.util.HashMap;

public interface ATMCommand {
    Account execute(HashMap<String, Account> registry, Scanner input, Account object);
}