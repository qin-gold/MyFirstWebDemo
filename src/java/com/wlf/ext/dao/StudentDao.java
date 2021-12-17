package com.wlf.ext.dao;

import com.wlf.ext.domain.Student;
import com.wlf.core.domain.dto.Result;

import java.io.IOException;

/**
 * 学生管理接口
 *
 * @author QinShijiao
 * @version 1.0
 */
public interface StudentDao {
    /**
     * 根据用户id查询数据
     *
     * @param id
     * @return
     * @throws IOException
     */
    Result findById(String id);

    /**
     * 分页查询所有学生的方法
     *
     * @param page
     * @param size
     * @param str
     * @return
     */
    Result findAll(int page, int size, String str);

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
