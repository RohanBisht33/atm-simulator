package com.bank.exception;

public class MaximumLimitReachedException extends RuntimeException {
    public MaximumLimitReachedException(String message) {
        super(message);
    }
}
