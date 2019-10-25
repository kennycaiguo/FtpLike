package com.ftplike.db;

import com.ftplike.model.User;
import java.io.File;

public interface DBase {
    public Boolean containsLogin(String login);
    public Boolean containsMail(String email);
    public void insertUser(String login, String email, String password, String homedir);
    public User getUser(String login);
}