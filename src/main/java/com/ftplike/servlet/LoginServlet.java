package com.ftplike.servlet;

import com.ftplike.error.IncorrectLoginException;
import com.ftplike.error.IncorrectPasswordException;
import com.ftplike.model.User;
import com.ftplike.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private String userCookieName = "loginedUser";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ses = request.getSession();

        User user = (User) ses.getAttribute(userCookieName);

        if (user == null) {
            getServletContext().getRequestDispatcher("/templates/login.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        try {
            request.getSession().setAttribute("isLgnForm", true);

            LoginService loginService = new LoginService();
            User user = loginService.Login(login, pass);

            request.getSession().setAttribute(userCookieName, user);
            response.sendRedirect(request.getContextPath() + "/");
        } catch (IncorrectLoginException e) {
            request.getSession().setAttribute("errLog", true);
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (IncorrectPasswordException e) {
            request.getSession().setAttribute("errPass", true);
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
