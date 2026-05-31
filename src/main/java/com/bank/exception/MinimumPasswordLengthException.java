package com.bank.exception;

public class MinimumPasswordLengthException extends RuntimeException {
    public MinimumPasswordLengthException(String message) {
        super(message);
    }
}
