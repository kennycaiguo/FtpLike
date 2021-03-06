package com.ftplike.service;

import com.ftplike.db.DBase;
import com.ftplike.db.HibernateUnit;
import com.ftplike.error.IncorrectFormInputException;
import com.ftplike.model.User;
import java.io.File;

public class RegisterService {
    private static RegisterService instance;

    private RegisterService(){}

    public static RegisterService getInstance(){
        RegisterService localInstance = instance;
        if (localInstance == null) {
            synchronized (RegisterService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    localInstance = instance = new RegisterService();
                }
            }
        }
        return localInstance;
    }

    public User register(String login, String email, String pass) throws IncorrectFormInputException {
        String home = "";
        if(System.getenv("FTPLIKE_HOME") == null){
            home = "rootdir";
        }

        DBase base = new HibernateUnit();
        if (!base.containsLogin(login)) {
            if (!base.containsMail(email)) {
                File homedir = new File(home + "/" + login);
                homedir.mkdir();
                base.insertUser(login, email, pass);
                return new User(login, email, pass);
            } else {
                throw new IncorrectFormInputException();
            }
        } else {
            throw new IncorrectFormInputException();
        }
    }
}