package com.bank.persistence;

import com.bank.Account;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class AccountSerializer extends StdSerializer<Account> {

    public AccountSerializer() {
        super(Account.class);
    }

    @Override
    public void serialize(Account account, JsonGenerator gen, SerializerProvider provider) throws IOException {
        ReentrantLock lock = account.getLock();
        lock.lock();
        try {
            gen.writeStartObject();
            gen.writeStringField("name", account.getName());
            gen.writeStringField("password", account.getPassword());
            gen.writeNumberField("balance", account.getBalance());
            gen.writeEndObject();
        } finally {
            lock.unlock();
        }
    }
}