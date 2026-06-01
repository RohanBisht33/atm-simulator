package com.bank;
import com.bank.exception.*;

import java.lang.String;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private String name;
    private String userid;
    private double balance;
    private String password;
    private static final double MAX_LIMIT = 50000;
    private final ReentrantLock lock = new ReentrantLock();

    public Account(){}
    public Account(String name, String userid, String password, double balance){
        this.name = name;
        this.userid = userid;
        this.password = password;
        this.balance = balance;
    }
    public String getUserid() {
        return userid;
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public double getBalance(){
        return balance;
    }
    public void setuserid(String userid) {
        this.userid = userid;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }

    public ReentrantLock getLock(){
        return this.lock;
    }

    public double showBalance(){
        return this.balance;
    }
    public boolean Withdraw(double amount){
        if (amount >= 0 && amount < MAX_LIMIT && amount <= this.balance){
            this.balance -= amount;
            return true;
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
    public boolean Deposit(double amount){
        if (amount >= 0 && amount < MAX_LIMIT){
            this.balance += amount;
            return true;
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

