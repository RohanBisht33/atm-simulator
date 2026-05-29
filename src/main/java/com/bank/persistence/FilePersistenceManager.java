package com.bank.persistence;
import com.bank.Account;

import java.io.*;
import java.util.HashMap;

public class FilePersistenceManager {

    public static void saveUser(Account object) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter("storage.txt", true));
        writer.append(object.toString());
        writer.newLine();
        writer.close();
    }

    public static void saveData(HashMap<String, Account> registry) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("storage.txt"))) {

            for (Account account : registry.values()) {
                writer.write(account.toString());
                writer.newLine();
            }
        }
    }

    public static HashMap<String, Account> loadData() throws IOException{
        HashMap<String, Account> object = new HashMap<>();

        File file = new File("storage.txt");

        if (!file.exists()) {
            file.createNewFile();
        }
        try{
            BufferedReader reader = new BufferedReader(new FileReader("storage.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                String name = tokens[0];
                double balance = Double.parseDouble(tokens[1]);
                object.put(name, new Account(name,balance));

            }
            reader.close();
        }
        catch (IOException e){
            System.out.println("IO Exception occurred!!");
        }
        return object;
    }
}
