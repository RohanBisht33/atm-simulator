package com.bank;
import com.bank.exception.*;

import java.lang.String;

public class Account {

    String name;
    private double balance;
    private static final double MAX_LIMIT = 50000;

    public Account(String name, double balance){
        this.name = name;
        this.balance = balance;
    }

    public double showBalance(){
        return this.balance;
    }
    public void Withdraw(double amount){

        if (amount >= 0 && amount < MAX_LIMIT && amount <= this.balance){
            this.balance -= amount;
        }
        else if(amount < 0){
            throw new NegativeFundsException("Balance is: "+ this.balance + " Withdrawal amount is in negative!!");
        }
        else if (amount > MAX_LIMIT) {
            throw new MaximumLimitReachedException("Balance is: "+ this.balance + " Withdrawal amount is greater than maximum per day limit of "+MAX_LIMIT);
        }
        else{
            throw new InsufficientFundsException("Balance is: "+ this.balance + " Withdrawal amount is greater than balance!!");
        }
    }
    public void Deposit(double amount){
        if (amount >= 0 && amount < MAX_LIMIT){
            this.balance += amount;
        }
        else if(amount < 0){
            throw new NegativeFundsException("Balance is: "+ this.balance + " Deposit amount is in negative!!");
        }
        else if (amount > MAX_LIMIT) {
            throw new MaximumLimitReachedException("Balance is: "+ this.balance + " Deposit amount is greater than maximum per day limit of "+MAX_LIMIT);
        }
        else{
            throw new IllegalArgumentException("Invalid amount! Deposit a valid amount");
        }
    }
    @Override
    public String toString() {
        return this.name + "," + this.balance;
    }
}

