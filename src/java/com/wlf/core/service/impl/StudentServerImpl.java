package com.wlf.core.service.impl;

import com.wlf.core.dao.StudentDao;
import com.wlf.core.dao.impl.StudentDaoImpl;
import com.wlf.core.domain.Student;
import com.wlf.core.domain.dto.QueryConditions;
import com.wlf.core.service.StudentServer;
import com.wlf.core.domain.dto.Result;

public class StudentServerImpl implements StudentServer {
    private final StudentDao studentDao = new StudentDaoImpl();

    @Override
    public Result findById(String id) {
        return null;
    }

    @Override
    public Result findAll(QueryConditions query) {
        return studentDao.findAll(query.getPage(), query.getCount(), query.getStr());
    }

    @Override
    public Result insert(Student student) {
        return null;
    }

    @Override
    public Result update(Student student) {
        return null;
    }

    @Override
    public Result delete(String id) {
        return null;
    }
}
