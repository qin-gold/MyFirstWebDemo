package com.wlf.ext.service;

import com.wlf.ext.domain.Student;
import com.wlf.core.domain.dto.QueryConditions;
import com.wlf.core.domain.dto.Result;

public interface StudentService {
    /**
     * 根据用户id查询数据
     *
     * @param id
     * @return
     */
    Result findById(String id);

    /**
     * 分页查询所有学生的方法
     *
     * @param queryConditions
     * @return
     */
    Result findAll(QueryConditions queryConditions);


    /**
     * 插入一个学生的方法
     *
     * @param student
     * @return
     */
    Result insert(Student student);

    /**
     * 更新一个学生的方法
     *
     * @param student
     * @return
     */
    Result update(Student student);

    /**
     * 删除一个学生的方法
     *
     * @param id
     * @return
     */
    Result delete(String id);
}
