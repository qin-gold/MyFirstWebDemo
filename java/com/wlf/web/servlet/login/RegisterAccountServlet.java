package com.wlf.web.servlet.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注册账号的功能
 * @author QinShijiao
 * @version 1.0
 * @date 2021-04-29 9:19
 */
@WebServlet(name = "register",urlPatterns = "/register")
public class RegisterAccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(123);
    }
}
