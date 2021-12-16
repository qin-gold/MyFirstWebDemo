package com.wlf.core.dao.base.permission.impl;

import com.wlf.core.dao.base.permission.RoleDao;
import com.wlf.core.domain.base.permission.Role;
import com.wlf.core.domain.dto.Result;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 18:49
 */
public class RoleDaoImpl implements RoleDao {
    @Override
    public Result insert(Role role) {
        Result result = new Result();
        return result;
    }

    @Override
    public Result update(Role role) {
        return null;
    }

    @Override
    public Result del(String id) {
        return null;
    }

    @Override
    public Result findById(String id) {
        return null;
    }

    @Override
    public Result findByUId(String uId) {
//        String sql ="select "
        return new Result();
    }

    @Override
    public Result findAll() {
        return null;
    }
}
