package com.wlf.core.service;

import com.wlf.core.domain.base.Account;
import com.wlf.core.domain.base.dto.UserData;
import com.wlf.core.domain.dto.Result;

/**
 * 用户登录的操作接口
 *
 * @author QinShijiao
 * @version 1.0
 * @date 2021-04-27 15:42
 */
public interface LoginService {
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
    Result checkAccount(String username);

    /**
     * 注册账户的方法
     *
     * @param account
     * @return
     */
    Result register(Account account);

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
     * @param id 账户id
     * @return
     */
    Result delete(String id);

    /** 初始化用户数据
     *
     * @param id 用户id
     * @return
     */
    UserData init(String id);
}
