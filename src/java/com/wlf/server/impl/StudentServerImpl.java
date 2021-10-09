package com.wlf.server.impl;

import com.wlf.dao.StudentDao;
import com.wlf.dao.impl.StudentDaoImpl;
import com.wlf.domain.Student;
import com.wlf.domain.dto.QueryConditions;
import com.wlf.server.StudentServer;
import com.wlf.domain.dto.Result;

public class StudentServerImpl implements StudentServer {
    private final StudentDao studentDao =new StudentDaoImpl();

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
