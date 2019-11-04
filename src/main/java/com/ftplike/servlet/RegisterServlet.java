package com.ftplike.servlet;

import com.ftplike.error.IncorrectFormInputException;
import com.ftplike.model.User;
import com.ftplike.service.RegisterService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private String userCookieName = "users";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("isLogin", false);

        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        try {
            request.getSession().setAttribute("isLgnForm", false);

            User user = RegisterService.getInstance().register(login, email, pass);

            request.getSession().setAttribute(userCookieName, user);
            response.sendRedirect(request.getContextPath() + "/");
        } catch (IncorrectFormInputException e) {
            response.getWriter().write("error");
//            request.getSession().setAttribute("err", true);
//            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}