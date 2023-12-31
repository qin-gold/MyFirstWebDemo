package com.wlf.web.base.servlet.login;

import com.wlf.annotation.Servlet;
import com.wlf.web.base.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注册账号的功能
 *
 * @author QinShijiao
 * @version 1.0
 * @date 2021-04-29 9:19
 */
@Servlet(mapping = "/register")
public class RegisterAccountServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
