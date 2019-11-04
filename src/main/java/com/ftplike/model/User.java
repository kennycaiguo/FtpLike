package com.ftplike.model;

public class User {
    private String login;
    private String email;
    private String pass;
    private int id;

    public User(){}

    public User(String login, String email, String pass){
        this.login = login;
        this.email = email;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setId(int id) {
        this.id = id;
    }
}