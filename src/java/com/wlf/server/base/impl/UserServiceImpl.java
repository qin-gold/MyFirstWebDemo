package com.wlf.server.base.impl;

import com.wlf.dao.base.UserDao;
import com.wlf.dao.base.impl.UserDaoImpl;
import com.wlf.domain.dto.Result;
import com.wlf.server.base.UserService;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 18:51
 */
public class UserServiceImpl implements UserService {
    private final UserDao dao =new UserDaoImpl();
    @Override
    public Result findById(String id) {
        return dao.findById(id);
    }
}
