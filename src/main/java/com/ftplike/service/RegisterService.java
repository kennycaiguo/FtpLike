package com.ftplike.service;

import com.ftplike.db.DBase;
import com.ftplike.db.MySqlBase;
import com.ftplike.error.IncorrectFormInputException;
import com.ftplike.model.User;
import java.io.File;

public class RegisterService {
    public User Register(String login, String email, String pass) throws IncorrectFormInputException {
        PropertiesService props = PropertiesService.getInstance();

        String basename = props.getBaseName();
        String userbase = props.getUserBase();
        String passbase = props.getPassBase();
        String tablename = props.getTableName();
        String home = props.getHomedir();
        String url = props.getBaseUrl();

        DBase base = new MySqlBase(userbase, passbase, basename, url, tablename);
        if (!base.containsLogin(login)) {
            if (!base.containsMail(email)) {
                File homedir = new File(home + "/" + login);
                homedir.mkdir();
                base.insertUser(login, email, pass, homedir.getPath());
                return new User(login, email, pass, homedir.getPath());
            } else {
                throw new IncorrectFormInputException();
            }
        } else {
            throw new IncorrectFormInputException();
        }
    }
}