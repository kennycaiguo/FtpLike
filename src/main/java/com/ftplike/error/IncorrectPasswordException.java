package com.ftplike.error;

public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException(){
        super("incorrect password");
    }
}
