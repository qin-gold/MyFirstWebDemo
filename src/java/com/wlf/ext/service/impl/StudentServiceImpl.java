package com.wlf.ext.service.impl;

import com.wlf.ext.dao.StudentDao;
import com.wlf.ext.dao.impl.StudentDaoImpl;
import com.wlf.ext.domain.Student;
import com.wlf.core.domain.dto.QueryConditions;
import com.wlf.ext.service.StudentService;
import com.wlf.core.domain.dto.Result;

public class StudentServiceImpl implements StudentService {
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
