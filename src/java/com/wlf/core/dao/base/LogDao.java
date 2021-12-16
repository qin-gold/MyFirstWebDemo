package com.wlf.core.dao.base;

import com.wlf.core.domain.base.Log;
import com.wlf.core.domain.dto.Result;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 22:31
 */
public interface LogDao {
    Result findAllLog(String str);
    Result findAllLog();
    Result findOne(String id);
    Result save(Log log);
}
