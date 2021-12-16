package com.wlf.core.dao.base;

import com.wlf.core.domain.dto.Result;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 18:44
 */
public interface UserDao {
    Result findById(String id);
}
