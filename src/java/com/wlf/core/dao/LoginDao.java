package com.wlf.core.dao;

import com.wlf.core.domain.base.Account;
import com.wlf.core.domain.base.LoginStatus;
import com.wlf.core.domain.dto.Result;


/**
 * @author QinShijiao
 * @version 1.0
 * @updateTime 2021/4/26 23:02
 */
public interface LoginDao {
    /**
     * 用户登录的方法
     *
     * @param account
     * @return
     */
    Result login(Account account);

    /**
     * 检测用户是否存在
     *
     * @param username
     * @return
     */
    boolean checkAccount(String username);

    /**
     * 注册账户的方法
     *
     * @param account
     * @return
     */
    Result insert(Account account);

    /**
     * 更新账户
     *
     * @param account
     * @return
     */
    Result update(Account account);

    /**
     * 删除账户
     *
     * @param id
     * @return
     */
    Result delete(String id);

    /**
     * 用于判断用户是否已经登录
     *
     * @param username
     * @return
     */
    Result isLogin(String username);

    /**
     * 记录登录记录日志
     *
     * @param loginStatus
     * @return
     */
    void insertLog(LoginStatus loginStatus);

    /**
     * 登出清除方法
     *
     * @param id
     */
    void delLoginLog(String id);

    /**
     * 删除全部日志方法
     */
    void delAllLoginLog();
}
