package com.wlf.server.base;

import com.wlf.domain.base.User;
import com.wlf.domain.dto.Result;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 18:44
 */
public interface UserService {
    Result findById(String id);
}
