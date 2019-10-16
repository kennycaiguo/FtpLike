package com.ftplike.servlet;

import com.ftplike.error.IncorrectEmailException;
import com.ftplike.error.IncorrectLoginException;
import com.ftplike.model.User;
import com.ftplike.service.RegisterService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private String userCookieName = "loginedUser";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("isLogin", false);

        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        try {
            RegisterService registerService = new RegisterService();
            User user = registerService.Register(login, email, pass);

            request.getSession().setAttribute(userCookieName, user);
            response.sendRedirect(request.getContextPath() + "/");
        } catch (IncorrectLoginException e) {
            request.setAttribute("errLog", true);
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (IncorrectEmailException e) {
            request.setAttribute("errMail", true);
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}