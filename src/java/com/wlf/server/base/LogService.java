package com.wlf.server.base;

import com.wlf.domain.base.Log;
import com.wlf.domain.dto.Result;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 22:32
 */
public interface LogService {
    Result findAllLog(String str);
    Result findAllLog();
    Result findOne(String id);
    Result save(Log log);
}
