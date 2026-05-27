package com.bank;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Account obj;

        Scanner input  = new Scanner(System.in);
        System.out.println("0. Exit\n1. Withdraw\n2. Deposit\n3. Show Balance\n 4. Create Account");

        System.out.print("Enter the number to choose: ");
        int choice = input.nextInt();
    
        while(choice != 0) {
            choice = input.nextInt();

            switch(choice){
                case 0 -> {
                    System.out.println("Exiting ...");
                    choice = 0;
                }
                case 4 -> {
                    System.out.print("Enter your name: ");
                    String name = input.next();
                    System.out.print("Enter your balance: ");
                    int balance = input.nextInt();
                    obj = new Account(name, balance);
                }
                default -> System.out.println("Exiting ...");
            }
        }
//        Scanner input  = new Scanner(System.in);
//        double withdrawal_amount;
//
//        System.out.print("Enter the amount to withdraw: ");
//        withdrawal_amount = input.nextDouble();
//        Account obj = new Account();
//        try{
//            Account.Withdraw(withdrawal_amount);
//        }
//        catch (InsufficientFundsException e){
//            System.err.println("Insufficient Fund Custom Exception: \n"+ e.getMessage());
//        }
    }
}