package com.wlf.web.servlet.login;

import com.alibaba.fastjson.JSON;
import com.wlf.annotation.Servlet;
import com.wlf.domain.Account;
import com.wlf.server.LoginServer;
import com.wlf.server.impl.LoginServerImpl;
import com.wlf.utlis.Result;
import com.wlf.utlis.Inject;
import com.wlf.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "login",urlPatterns = "/login")
@Servlet(mapping = "/login")
public class LoginAccountServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.returnJson(req, resp);
        LoginServer login = new LoginServerImpl();
        Account account = Inject.getBean(req,Account.class);
        Result result = login.login(account);
        String json = JSON.toJSONString(result);
        resp.getWriter().write(json);
    }
}
