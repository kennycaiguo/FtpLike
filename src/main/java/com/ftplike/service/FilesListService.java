package com.ftplike.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class FilesListService {
    private ArrayList<File> Directories = new ArrayList<>();
    private ArrayList<File> Files = new ArrayList<>();
    private String ParentPath;

    public ArrayList<File> getDirectories() {
        return this.Directories;
    }

    public ArrayList<File> getFiles(){
        return this.Files;
    }

    public String getParent(){
        return this.ParentPath;
    }

    public boolean setLists(String path){
        try {
            File f = new File(path);
            File[] files = new File(path).listFiles();

            ParentPath = f.getParent();

            Arrays.sort(files);

            for (File file: files){
                if (file.isDirectory()) {
                    Directories.add(file);
                } else {
                    Files.add(file);
                }
            }

            return true;
        }
        catch (NullPointerException np){
            return false;
        }
    }
}