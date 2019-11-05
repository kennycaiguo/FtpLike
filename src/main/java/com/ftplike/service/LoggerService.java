package com.ftplike.service;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerService {
    public enum LogLevels{
        INFO,
        ERROR
    }

    private static final String path = System.getenv("FTPLIKE_BASE") == null
            ? "log/runtime.log"
            : System.getenv("FTPLIKE_BASE") + "/log/runtime.log";

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