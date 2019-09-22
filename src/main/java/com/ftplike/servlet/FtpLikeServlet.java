package com.ftplike.servlet;

import com.ftplike.service.FilesListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FtpLikeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("path");
        path = new String(path.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        FilesListService filesList = new FilesListService();

        System.out.println(path);

        if(filesList.setLists(path) && path != null){

            request.setAttribute("dirs", filesList.getDirectories());
            request.setAttribute("files", filesList.getFiles());
            request.setAttribute("parent", filesList.getParent());

            getServletContext().getRequestDispatcher("/templates/dirList.jsp").forward(request, response);
        }
        else{
            getServletContext().getRequestDispatcher("/templates/dirErr.jsp").forward(request, response);
        }
    }
}