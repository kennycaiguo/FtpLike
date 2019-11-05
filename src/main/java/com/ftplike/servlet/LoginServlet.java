package com.ftplike.servlet;

import com.ftplike.error.IncorrectFormInputException;
import com.ftplike.model.User;
import com.ftplike.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private String userCookieName = "users";

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
            User user = LoginService.getInstance().login(login, pass);

            request.getSession().setAttribute(userCookieName, user);
            response.getWriter().write("ok");
//            response.sendRedirect(request.getContextPath() + "/");
        } catch (IncorrectFormInputException e) {
            response.getWriter().write("error");
        }
    }
}