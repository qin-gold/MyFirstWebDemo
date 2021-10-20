package com.wlf.dao.base.permission;

import com.wlf.domain.base.permission.Role;
import com.wlf.domain.dto.Result;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 18:47
 */
public interface RoleDao {
    Result insert(Role role);

    Result update(Role role);

    Result del(String id);

    Result findById(String id);

    Result findByUId(String uId);

    Result findAll();
}
