package com.ftplike.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesService {
    private static PropertiesService instance;
    private final String path = "conf/config.properties";

    private String baseName;
    private String tableName;
    private String userBase;
    private String passBase;
    private String homedir;
    private String baseUrl;

    private PropertiesService(){
        Properties prop = new Properties();
        try(FileInputStream fis = new FileInputStream(new File(path).getAbsolutePath())){
            prop.load(fis);

            baseName = prop.getProperty("basename");
            tableName = prop.getProperty("tablename");
            userBase = prop.getProperty("userbase");
            passBase = prop.getProperty("passbase");
            homedir = prop.getProperty("homedir");
            baseUrl = prop.getProperty("url");
        }
        catch(Exception ex){}
    }

    public static PropertiesService getInstance(){
        PropertiesService localInstance = instance;
        if(localInstance == null){
            synchronized (PropertiesService.class){
                localInstance = instance;
                if(localInstance == null){
                    localInstance = instance = new PropertiesService();
                }
            }
        }
        return localInstance;
    }


    public String getBaseName(){
        return baseName;
    }
    public String getTableName(){
        return tableName;
    }
    public String getUserBase(){
        return userBase;
    }
    public String getPassBase() {
        return passBase;
    }
    public String getHomedir() {
        return homedir;
    }
    public String getBaseUrl() { return  baseUrl; }
}