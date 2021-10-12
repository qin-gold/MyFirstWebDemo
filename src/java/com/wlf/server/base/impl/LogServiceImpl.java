package com.wlf.server.base.impl;

import com.wlf.dao.base.LogDao;
import com.wlf.dao.base.impl.LogDaoImpl;
import com.wlf.domain.base.Log;
import com.wlf.domain.dto.Result;
import com.wlf.server.base.LogService;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 22:33
 */
public class LogServiceImpl implements LogService {
    private static final LogDao logDao = new LogDaoImpl();
    @Override
    public Result findAllLog(String str) {
        return null;
    }

    @Override
    public Result findAllLog() {
        return null;
    }

    @Override
    public Result findOne(String id) {
        return null;
    }

    @Override
    public Result save(Log log) {
        return logDao.save(log);
    }
}
