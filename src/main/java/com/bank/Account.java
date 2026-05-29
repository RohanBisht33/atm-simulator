package com.bank;
import java.lang.String;

public class Account {

    String name;
    private double balance;

    Account(String name, double balance){
        this.name = name;
        this.balance = balance;
    }

    public double showBalance(){
        return this.balance;
    }
    public void Withdraw(double amount){

        if (amount <= this.balance){
            this.balance -= amount;
        }
        else{
            throw new InsufficientFundsException("Balance is: "+ this.balance + " Withdrawal amount is greater than balance!!");
        }
    }
    public void Deposit(double amount){
        if (amount >= 1){
            this.balance += amount;
        }
        else{
            throw new IllegalArgumentException("Invalid amount! Deposit a valid amount");
        }
    }
}

