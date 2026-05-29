package com.bank;
import java.util.Scanner;
import java.util.HashMap;

public class Main{
    public static void main(String[] args){

        HashMap<String, Account> accountRegistry = new HashMap<>();
        Account obj = null;

        boolean islogged = false;

        Scanner input  = new Scanner(System.in);
        String choice = "";

        HashMap<String, ATMCommand> unauthMenu = new HashMap<>();
        unauthMenu.put("1", new LoginCommand());
        unauthMenu.put("2", new CreateCommand());

        HashMap<String, ATMCommand> authMenu = new HashMap<>();
        authMenu.put("1", new WithdrawCommand());
        authMenu.put("2", new DepositCommand());
        authMenu.put("3", new LogoutCommand());

        while(!choice.equals("0")) {
            if(!islogged){
                System.out.println("0. Exit\n1. Login\n2. Create Account");

                System.out.print("Enter the number to choose: ");
                choice = input.next();

                if(choice.equals("0")){
                        System.out.println("Exiting ...");
                        islogged = false;
                        obj = null;
                        choice = "0";
                }
                else if(unauthMenu.containsKey(choice)){
                        obj = unauthMenu.get(choice).execute(accountRegistry, input, null);
                        if(obj!=null) {
                            islogged = true;
                        }
                }
                else{
                    System.out.println("Invalid option!");
                }
            }
            else{
                System.out.println("0. Exit\n1. Withdraw\n2. Deposit\n3. Logout\n4. Show Balance");

                System.out.print("Enter the number to choose: ");
                choice = input.next();

                if(choice.equals("0")) {
                    System.out.println("Exiting ...");
                    islogged = false;
                    obj = null;
                }
                else if(choice.equals("4")){
                    System.out.println("You balance is: " + obj.showBalance());
                }
                else if(authMenu.containsKey(choice)){
                    obj = authMenu.get(choice).execute(accountRegistry, input, obj);
                    if (obj == null) {
                        islogged = false;
                    }
                }
                else{
                    System.out.println("Invalid option!");
                }
            }
        }
    }
}