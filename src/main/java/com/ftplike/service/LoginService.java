package com.ftplike.service;

import com.ftplike.db.DBase;
import com.ftplike.db.HibernateUnit;
import com.ftplike.db.MySqlBase;
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
        PropertiesService props = PropertiesService.getInstance();

        String basename = props.getBaseName();
        String userbase = props.getUserBase();
        String passbase = props.getPassBase();
        String url = props.getBaseUrl();
        String tablename = props.getTableName();

//        DBase base = new MySqlBase(userbase, passbase, basename, url, tablename);
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