package com.ftplike.model;

import java.io.File;

public class User {
    private String login;
    private String email;
    private String password;
    private File homedir;

    public User(String login, String email, String password, File homedir){
        this.login = login;
        this.email = email;
        this.password = password;
        this.homedir = homedir;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public File getHomedir() {
        return homedir;
    }
}