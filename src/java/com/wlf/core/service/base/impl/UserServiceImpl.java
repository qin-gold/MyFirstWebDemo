package com.wlf.core.service.base.impl;

import com.wlf.core.dao.base.UserDao;
import com.wlf.core.dao.base.impl.UserDaoImpl;
import com.wlf.core.domain.dto.Result;
import com.wlf.core.service.base.UserService;

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
