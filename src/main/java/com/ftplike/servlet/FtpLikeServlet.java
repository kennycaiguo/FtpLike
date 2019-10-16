package com.ftplike.servlet;

import com.ftplike.model.FilesList;
import com.ftplike.model.User;
import com.ftplike.service.FilesListService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class FtpLikeServlet extends HttpServlet {
    private String userCookieName = "loginedUser";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession ses = request.getSession();
            User us = (User)ses.getAttribute(userCookieName);

            String path = request.getParameter("path");

            if (path == null) {
                path = us.getHomedir().getAbsolutePath();
            }

            path = new String(path.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            FilesList list = new FilesListService().readPath(path);

            System.out.println(path);
            System.out.println(us.getHomedir().getPath());


            if (path.contains(us.getHomedir().getPath())) {
                request.setAttribute("dirs", list.getDirectories());
                request.setAttribute("files", list.getFiles());
                request.setAttribute("parent", list.getParent().getAbsolutePath());
                request.setAttribute("homedir", us.getHomedir().getParentFile().getAbsolutePath());
                request.setAttribute("uri", request.getRequestURI());

                getServletContext().getRequestDispatcher("/templates/dirList.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/templates/dirErr.jsp").forward(request, response);
            }
        } catch (NullPointerException ex) {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute(userCookieName, null);
        response.sendRedirect(request.getContextPath() + "/");
    }
}