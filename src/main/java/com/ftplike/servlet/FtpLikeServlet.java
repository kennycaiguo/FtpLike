package com.ftplike.servlet;

import com.ftplike.model.FilesList;
import com.ftplike.service.FilesListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;


public class FtpLikeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = request.getParameter("path");
            path = new String(path.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            System.out.println(path);

            FilesList list = new FilesListService().readPath(path);

            request.setAttribute("dirs", list.getDirectories());
            request.setAttribute("files", list.getFiles());
            request.setAttribute("parent", list.getParent());
            request.setAttribute("uri", request.getRequestURI());

            getServletContext().getRequestDispatcher("/templates/dirList.jsp").forward(request, response);
        } catch (NullPointerException ex) {
            getServletContext().getRequestDispatcher("/templates/dirErr.jsp").forward(request, response);
        }
    }
}