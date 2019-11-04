package com.ftplike.servlet;

import com.ftplike.model.FilesList;
import com.ftplike.model.User;
import com.ftplike.service.FilesListService;
import com.ftplike.service.LoggerService;
import com.ftplike.service.PropertiesService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class FtpLikeServlet extends HttpServlet {
    private String userCookieName = "users";

    private static void read(File f) {
        try (FileReader fr = new FileReader(f)) {
            char[] buff = new char[256];
            int c;
            while ((c = fr.read(buff)) > 0){
                buff = Arrays.copyOf(buff, c);
            }
            System.out.print(buff);

        } catch (Exception e) {
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String servhome = System.getenv("FTPLIKE_HOME");

            if(servhome == null){
                servhome = "";
            }

            HttpSession ses = request.getSession();
            User us = (User) ses.getAttribute(userCookieName);

            String userdir = servhome  + PropertiesService.getInstance().getHomedir() + "/" + us.getLogin();
            String path = request.getParameter("path");

            if (path == null) {
                path = new File(userdir).getAbsolutePath() + "/";
            }

            path = new String(path.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            FilesList list = FilesListService.getInstance().readPath(path);
            LoggerService.log(LoggerService.LogLevels.INFO, us.getLogin() + " - " + path);

            path += "/";

            if (path.contains(new File(userdir).getAbsolutePath() + "/") && list != null) {
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