package com.bank.command;
import com.bank.Account;

import java.util.Scanner;
import java.util.HashMap;

public interface ATMCommand {
    Account execute(HashMap<String, Account> registry, Scanner input, Account object);
}