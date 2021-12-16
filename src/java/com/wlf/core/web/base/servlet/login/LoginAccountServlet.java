package com.wlf.core.web.base.servlet.login;

import com.alibaba.fastjson.JSON;
import com.wlf.core.annotation.Log;
import com.wlf.core.domain.base.Account;
import com.wlf.core.domain.base.dto.UserData;
import com.wlf.core.domain.dto.Result;
import com.wlf.core.domain.dto.ReturnMsg;
import com.wlf.core.service.base.AccountService;
import com.wlf.core.service.base.impl.AccountServiceImpl;
import com.wlf.core.utlis.CacheUtils;
import com.wlf.core.utlis.Inject;
import com.wlf.core.web.base.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log(title = "用户登录",remark = "用户登录")
//@Servlet(mapping = "/login")
@WebServlet("/login")
public class LoginAccountServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AccountService login = new AccountServiceImpl();
        Account account = Inject.getBean(req, Account.class);
        Result result = login.login(account);
        Account data = (Account)result.getData();
        if (data!=null){
            CacheUtils.setCache(data.getUserId(),new UserData(data.getUserId(),req));
        }
        super.returnJson(req, resp,JSON.toJSONString(new ReturnMsg(result.getCode(),result.getMsg())));
    }

}
