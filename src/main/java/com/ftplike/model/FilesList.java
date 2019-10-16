package com.ftplike.model;

import java.io.File;
import java.util.ArrayList;

public class FilesList {
    private ArrayList<File> Directories;
    private ArrayList<File> Files;
    private File Parent;

    public FilesList(File parentPath, ArrayList dirs, ArrayList files){
        this.Parent = parentPath;
        this.Directories = dirs;
        this.Files = files;
    }

    public ArrayList<File> getDirectories() {
        return this.Directories;
    }

    public ArrayList<File> getFiles() {
        return this.Files;
    }

    public File getParent() {
        return this.Parent;
    }
}
