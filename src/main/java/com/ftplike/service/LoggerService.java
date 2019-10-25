package com.ftplike.service;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerService {
    private static final String path = "log/runtime.log";
    public enum LogLevels{
        INFO,
        ERROR
    }

    private static void logWrite(String msg){
        try(FileWriter fw = new FileWriter(path, true)){
            fw.write(msg);
            fw.write('\n');
        }
        catch (Exception ex){}
    }

    public static void log(LogLevels lvl, String msg){
        logWrite("[" + lvl + "] " + new SimpleDateFormat("dd.MM.yyyy HH:mm.ss").format(new Date()) + " | " + msg);
    }
}