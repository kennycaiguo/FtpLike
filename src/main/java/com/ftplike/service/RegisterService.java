package com.ftplike.service;

import com.ftplike.db.DBase;
import com.ftplike.db.MySqlBase;
import com.ftplike.db.SQLBase;
import com.ftplike.error.IncorrectEmailException;
import com.ftplike.error.IncorrectLoginException;
import com.ftplike.model.User;
import java.io.File;

public class RegisterService {
    public User Register(String login, String email, String pass) throws IncorrectEmailException, IncorrectLoginException {
//        DBase base = new SQLBase();
        DBase base = new MySqlBase();
        if (!base.containsLogin(login)) {
            if (!base.containsMail(email)) {
                File homedir = new File("homedir/" + login);
                homedir.mkdir();
                base.insertUser(login, email, pass, homedir);
                System.out.println(login);
                return new User(login, email, pass, homedir);
            } else {
                throw new IncorrectEmailException();
            }
        } else {
            throw new IncorrectLoginException();
        }
    }
}