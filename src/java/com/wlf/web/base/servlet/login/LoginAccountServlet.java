package com.wlf.web.base.servlet.login;

import com.alibaba.fastjson.JSON;
import com.wlf.annotation.Servlet;
import com.wlf.domain.base.Account;
import com.wlf.server.LoginServer;
import com.wlf.server.impl.LoginServerImpl;
import com.wlf.domain.dto.Result;
import com.wlf.utlis.Inject;
import com.wlf.web.base.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Servlet(mapping = "/login")
public class LoginAccountServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginServer login = new LoginServerImpl();
        Account account = Inject.getBean(req, Account.class);
        super.returnJson(req, resp,login.login(account).toJson(login.login(account)));
    }
}
