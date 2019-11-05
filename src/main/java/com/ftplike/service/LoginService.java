package com.ftplike.service;

import com.ftplike.db.DBase;
import com.ftplike.db.HibernateUnit;
import com.ftplike.error.IncorrectFormInputException;
import com.ftplike.model.User;

public class LoginService {
    private static LoginService instance;

    private LoginService(){
    }

    public static LoginService getInstance(){
        LoginService localInstance = instance;
        if (localInstance == null) {
            synchronized (LoginService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    localInstance = instance = new LoginService();
                }
            }
        }
        return localInstance;
    }

    public User login(String login, String pass) throws IncorrectFormInputException {
        DBase base = new HibernateUnit();
        if (base.containsLogin(login) || base.containsMail(login)) {
            User us = base.getUser(login);

            if (us.getPass().equals(pass)) {
                return us;
            } else {
                throw new IncorrectFormInputException();
            }
        } else {
            throw new IncorrectFormInputException();
        }
    }
}