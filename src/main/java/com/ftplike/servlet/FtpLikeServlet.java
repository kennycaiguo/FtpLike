package com.ftplike.servlet;

import com.ftplike.model.FilesList;
import com.ftplike.model.User;
import com.ftplike.service.FilesListService;
import com.ftplike.service.LoggerService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;

public class FtpLikeServlet extends HttpServlet {
    private String userCookieName = "users";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession ses = request.getSession();
            User us = (User)ses.getAttribute(userCookieName);

            String userdir = System.getenv("FTPLIKE_HOME") == null
                    ? "rootdir/" + us.getLogin()
                    : System.getenv("FTPLIKE_HOME") + "/" + us.getLogin();

            String path = request.getParameter("path");

            if (path == null) {
                path = new File(userdir).getAbsolutePath() + "/";
            }

            FilesList list = FilesListService.getInstance().readPath(path);
            LoggerService.log(LoggerService.LogLevels.INFO, us.getLogin() + " - " + path);

            if ((path + "/").contains(new File(userdir).getAbsolutePath() + "/") && list != null) {
                request.setAttribute("dirs", list.getDirectories());
                request.setAttribute("files", list.getFiles());
                request.setAttribute("parent", list.getParent().getAbsolutePath());
                request.setAttribute("homedir", new File(userdir).getParentFile().getAbsolutePath());
                request.setAttribute("usname", us.getLogin());
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