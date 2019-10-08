package com.ftplike.service;

import com.ftplike.model.FilesList;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FilesListService {
    public FilesList readPath(String path) throws NullPointerException {
        ArrayList Directories = new ArrayList<File>();
        ArrayList Files = new ArrayList<File>();

        File f = new File(path);
        File[] files = new File(path).listFiles();

        Arrays.sort(files);

        for (File file : files) {
            if (file.isDirectory()) {
                Directories.add(file);
            } else {
                Files.add(file);
            }
        }

        return new FilesList(f.getParent(), Directories, Files);
    }
}