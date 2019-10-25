package com.ftplike.service;

import com.ftplike.db.DBase;
import com.ftplike.db.MySqlBase;
import com.ftplike.error.IncorrectFormInputException;
import com.ftplike.model.User;

public class LoginService {
    public User Login(String login, String pass) throws IncorrectFormInputException {
        PropertiesService props = PropertiesService.getInstance();

        String basename = props.getBaseName();
        String userbase = props.getUserBase();
        String passbase = props.getPassBase();
        String url = props.getBaseUrl();
        String tablename = props.getTableName();

        DBase base = new MySqlBase(userbase, passbase, basename, url, tablename);
        if (base.containsLogin(login) || base.containsMail(login)) {
            User us = base.getUser(login);

            if (us.getPassword().equals(pass)) {
                return us;
            } else {
                throw new IncorrectFormInputException();
            }
        } else {
            throw new IncorrectFormInputException();
        }
    }
}