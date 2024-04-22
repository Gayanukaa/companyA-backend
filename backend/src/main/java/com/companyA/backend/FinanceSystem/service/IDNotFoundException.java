package com.companyA.backend.FinanceSystem.service;

public class IDNotFoundException extends RuntimeException{
    public IDNotFoundException(String message) {
        super(message);
    }

    public IDNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IDNotFoundException(Throwable cause) {
        super(cause);
    }
}