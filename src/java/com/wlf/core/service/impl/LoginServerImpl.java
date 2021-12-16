package com.wlf.core.service.impl;

import cn.hutool.crypto.SmUtil;
import com.wlf.core.dao.LoginDao;
import com.wlf.core.dao.impl.LoginDaoImpl;
import com.wlf.core.domain.base.Account;
import com.wlf.core.domain.base.dto.UserData;
import com.wlf.core.service.LoginServer;
import com.wlf.core.domain.dto.Result;

/**
 * @author QinShijiao
 * @version 1.0
 * @date 2021-04-27 15:42
 */
public class LoginServerImpl implements LoginServer {
    private final LoginDao loginDao =new LoginDaoImpl();
    @Override
    public Result login(Account account) {
        account.setPassword(SmUtil.sm3(account.getPassword()));
        return loginDao.login(account);
    }

    @Override
    public Result checkAccount(String username) {
        return null;
    }

    @Override
    public Result register(Account account) {
        return null;
    }

    @Override
    public Result update(Account account) {
        return null;
    }

    @Override
    public Result delete(String id) {
        return null;
    }

    @Override
    public UserData init(String id) {
        return null;
    }
}
