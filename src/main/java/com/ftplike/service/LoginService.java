package com.ftplike.service;

import com.ftplike.db.DBase;
import com.ftplike.db.MySqlBase;
import com.ftplike.db.SQLBase;
import com.ftplike.error.IncorrectLoginException;
import com.ftplike.error.IncorrectPasswordException;
import com.ftplike.model.User;

public class LoginService {
    public User Login(String login, String pass) throws IncorrectLoginException, IncorrectPasswordException {
//        DBase base = new SQLBase();
        DBase base = new MySqlBase();
        if (base.containsLogin(login) || base.containsMail(login)) {
            User us = base.getUser(login);

            if (us.getPassword().equals(pass)) {
                return us;
            } else {
                throw new IncorrectPasswordException();
            }
        } else {
            throw new IncorrectLoginException();
        }
    }
}