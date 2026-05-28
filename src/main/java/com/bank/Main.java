package com.bank;
import java.util.Scanner;
import java.util.HashMap;
import java.util.InputMismatchException;

public class Main{
    public static void main(String[] args){

        HashMap<String, Account> accountRegistry = new HashMap<>();
        Account obj = null;

        boolean islogged = false;
        String balance, amount;

        Scanner input  = new Scanner(System.in);
        String choice = "";
    
        while(!choice.equals("0")) {
            if(!islogged){
                System.out.println("0. Exit\n1. Login\n2. Create Account");

                System.out.print("Enter the number to choose: ");
                choice = input.next();

                switch (choice) {
                    case "0" -> {
                        System.out.println("Exiting ...");
                        islogged = false;
                        obj = null;
                        choice = "0";

                    }
                    case "1" -> {
                        System.out.print("Enter your name: ");
                        String name = input.next();
                        if (accountRegistry.containsKey(name)) {
                            System.out.println("Logged in Successfully!\nWelcome " + name);
                            obj = accountRegistry.get(name);
                            islogged = true;
                        } else {
                            System.out.println("The user does not exist!!");
                        }
                    }
                    case "2" -> {
                        System.out.print("Enter your name: ");
                        String name = input.next();
                        System.out.print("Enter your balance: ");

                        try{
                            balance = input.next();
                            obj = new Account(name, Double.parseDouble(balance));
                            accountRegistry.put(name, obj);
                            islogged = true;
                        }
                        catch(NumberFormatException e){
                            System.out.println("Wrong input, enter only numeric values!!");
                        }
                    }
                    default -> System.out.println("Invalid option!");
                }
            }
            else{
                System.out.println("0. Exit\n1. Logout\n2. Withdraw\n3. Deposit\n4. Show Balance");

                System.out.print("Enter the number to choose: ");
                choice = input.next();

                switch (choice) {
                    case "0" -> {
                        System.out.println("Exiting ...");
                        islogged = false;
                        obj = null;
                        choice = "0";
                    }
                    case "1" -> {
                        System.out.println("You are logged out now! ");
                        islogged = false;
                        obj = null;
                        choice = "";
                    }
                    case "2" -> {
                        System.out.print("Enter the amount to withdraw: ");

                        try{
                            amount = input.next();
                            if (obj!=null){
                                try{
                                    obj.Withdraw(Double.parseDouble(amount));
                                }
                                catch(InsufficientFundsException e){
                                    System.out.println("Kindly Withdraw money under balance limit ^_^");
                                }
                            }
                        }
                        catch(NumberFormatException e){
                            System.out.println("Wrong input, enter only numeric values!!");
                        }
                    }
                    case "3" -> {
                        System.out.print("Enter the amount to deposit: ");
                        try{
                            amount = input.next();
                            if (obj!=null) {
                                obj.Deposit(Double.parseDouble(amount));
                            }
                        }
                        catch(NumberFormatException e){
                            System.out.println("Wrong input, enter only numeric values!!");
                        }

                    }
                    case "4" -> {
                        if(obj!=null){
                            System.out.println("You balance is: " + obj.showBalance());
                        }
                    }
                    default -> System.out.println("Invalid option!");
                }
            }
        }
    }
}