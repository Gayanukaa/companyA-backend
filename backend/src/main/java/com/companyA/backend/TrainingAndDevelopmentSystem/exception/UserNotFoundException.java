package com.companyA.backend.TrainingAndDevelopmentSystem.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String id){
        super("Could not found "+id);
    }

}
