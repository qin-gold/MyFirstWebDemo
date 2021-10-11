package com.wlf.server;

import com.wlf.domain.Student;
import com.wlf.domain.dto.QueryConditions;
import com.wlf.domain.dto.Result;

public interface StudentServer {
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
