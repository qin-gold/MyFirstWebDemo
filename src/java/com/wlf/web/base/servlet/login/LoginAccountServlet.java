package com.wlf.web.base.servlet.login;

import com.alibaba.fastjson.JSON;
import com.wlf.annotation.Log;
import com.wlf.annotation.Servlet;
import com.wlf.domain.base.Account;
import com.wlf.domain.base.dto.UserData;
import com.wlf.domain.dto.Result;
import com.wlf.domain.dto.ReturnMsg;
import com.wlf.server.LoginServer;
import com.wlf.server.impl.LoginServerImpl;
import com.wlf.utlis.CacheUtils;
import com.wlf.utlis.Inject;
import com.wlf.web.base.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log(title = "用户登录",remark = "用户登录")
@Servlet(mapping = "/login")
public class LoginAccountServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginServer login = new LoginServerImpl();
        Account account = Inject.getBean(req, Account.class);
        Result result = login.login(account);
        Account data = (Account)result.getData();
        if (data!=null){
            CacheUtils.setCache(data.getUserId(),new UserData());
        }
        super.returnJson(req, resp,JSON.toJSONString(new ReturnMsg(result.getCode(),result.getMsg())));
    }
}
