package com.bank.exception;

public class NegativeFundsException extends RuntimeException{

    public NegativeFundsException(String message) {
        super(message);
    }

}
