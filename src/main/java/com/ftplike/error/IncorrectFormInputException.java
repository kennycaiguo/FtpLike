package com.ftplike.error;

public class IncorrectFormInputException extends RuntimeException {
    public IncorrectFormInputException(){
        super("Incorrect Input Value");
    }
}
