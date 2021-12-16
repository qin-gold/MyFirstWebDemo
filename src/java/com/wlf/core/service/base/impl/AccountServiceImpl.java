package com.wlf.core.service.base.impl;

import cn.hutool.crypto.SmUtil;
import com.wlf.core.dao.base.AccountDao;
import com.wlf.core.dao.base.LoginStatusDao;
import com.wlf.core.dao.base.impl.AccountDaoImpl;
import com.wlf.core.dao.base.impl.LoginStatusDaoImpl;
import com.wlf.core.dao.base.permission.RoleDao;
import com.wlf.core.dao.base.permission.impl.RoleDaoImpl;
import com.wlf.core.domain.base.Account;
import com.wlf.core.domain.base.dto.UserData;
import com.wlf.core.domain.dto.Result;
import com.wlf.core.service.base.AccountService;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 18:50
 */
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao = new AccountDaoImpl();
    private final RoleDao roleDao = new RoleDaoImpl();
    private final LoginStatusDao loginStatusDao = new LoginStatusDaoImpl();

    @Override
    public Result login(Account account) {
        account.setPassword(SmUtil.sm3(account.getPassword()));
        return accountDao.login(account);
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
