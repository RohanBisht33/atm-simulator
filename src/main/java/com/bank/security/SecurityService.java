package com.bank.security;

import com.bank.Account;
import com.bank.exception.MinimumPasswordLengthException;
import com.bank.exception.NegativeFundsException;
import org. springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityService {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String hashPassword(String password) {
        if (password == null || password.length() < 8) {
            throw new MinimumPasswordLengthException("Password length is less than 8 digits!");
        }

        System.out.println("Password has been created ^^");
        return encoder.encode(password);
    }
    public static double checkBalance(String balance) {
        double amount = Double.parseDouble(balance);
        if(amount < 0){
            throw new NegativeFundsException("Initial amount is in negative!!");
        }
        return amount;
    }

    public static Account unhashPassword(String password, Account object){

        if(encoder.matches(password, object.getPassword())){
            System.out.println("You have successfully logged in!!");
            return object;
        }
        else{
            System.out.println("You have entered wrong password");
            return null;
        }
    }
}