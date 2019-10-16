package com.ftplike.error;

public class IncorrectLoginException extends Exception {
    public IncorrectLoginException(){
        super("incorrect login");
    }
}
