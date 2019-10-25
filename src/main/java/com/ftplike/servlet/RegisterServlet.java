package com.ftplike.servlet;

import com.ftplike.error.IncorrectFormInputException;
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
            request.getSession().setAttribute("isLgnForm", false);

            RegisterService registerService = new RegisterService();
            User user = registerService.Register(login, email, pass);

            request.getSession().setAttribute(userCookieName, user);
            response.sendRedirect(request.getContextPath() + "/");
        } catch (IncorrectFormInputException e) {
            request.getSession().setAttribute("err", true);
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}