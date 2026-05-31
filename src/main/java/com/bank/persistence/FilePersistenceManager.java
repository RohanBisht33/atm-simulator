package com.bank.persistence;
import com.bank.Account;

import java.io.*;
import java.util.HashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class FilePersistenceManager {

    private static final File file = new File("accounts.json");
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Account.class, new AccountSerializer());
        mapper.registerModule(module);
    }

//    public static void saveUser(Account object) throws IOException{
//        BufferedWriter writer = new BufferedWriter(new FileWriter("storage.txt", true));
//        writer.append(object.toString());
//        writer.newLine();
//        writer.close();
//    }

    public static void saveData(HashMap<String, Account> registry) throws IOException {

//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("storage.txt"))) {
//
//            for (Account account : registry.values()) {
//                writer.write(account.toString());
//                writer.newLine();
//            }
//        }
        HashMap<String, Account> snapshot;

        // 1. Thread-safe snapshot creation
        synchronized (registry) {
            snapshot = new HashMap<>(registry);
        }
        mapper.writerWithDefaultPrettyPrinter().writeValue(file,snapshot);
    }

    public static HashMap<String, Account> loadData() throws IOException{
//        HashMap<String, Account> object = new HashMap<>();
//
//        File file = new File("storage.txt");
//
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//        try{
//            BufferedReader reader = new BufferedReader(new FileReader("storage.txt"));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] tokens = line.split(",");
//                String name = tokens[0];
//                String password = tokens[1];
//                double balance = Double.parseDouble(tokens[2]);
//                object.put(name, new Account(name,password, balance));
//
//            }
//            reader.close();
//        }
//        catch (IOException e){
//            System.out.println("IO Exception occurred!!");
//        }
//        return object;

        if(!file.exists()){
            return new HashMap<>();
        }

        return mapper.readValue(file, new TypeReference<HashMap<String, Account>>() {});
    }
}
