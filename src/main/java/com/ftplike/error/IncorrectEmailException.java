package com.ftplike.error;

public class IncorrectEmailException extends Exception {
    public IncorrectEmailException(){
        super("incorrect email");
    }
}
