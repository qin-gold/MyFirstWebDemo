package com.wlf.core.dao.impl;

import com.wlf.core.dao.StudentDao;
import com.wlf.core.domain.Student;
import com.wlf.core.enums.CodeEnum;
import com.wlf.core.enums.MsgCode;
import com.wlf.core.utlis.JDBCUtils;
import com.wlf.core.domain.dto.Result;
import com.wlf.core.domain.dto.ReturnMsg;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 学生操作类
 *
 * @author Qin ShiJiao
 * @createTime 2021/4/27 0:12
 */
public class StudentDaoImpl implements StudentDao {

    @Override
    public Result findById(String id) {
        Result result = new Result();
        String sql = "select * from Student where id = ?";
        List<Map<String, Object>> maps = JDBCUtils.queryForList(sql, id);
        if (!maps.isEmpty()) {
            result.setCode(CodeEnum.SUCCESS.getStatusZh());
            result.setMsg(new ReturnMsg(MsgCode.MSG001).getMsg());
            result.setData(maps);
        } else {
            result.setCode(CodeEnum.FAULT.getStatusZh());
            result.setMsg(new ReturnMsg(MsgCode.ERR001).getMsg());
            result.setData(null);
        }
        return result;
    }

    @Override
    public Result findAll(int page, int size, String str) {
        Result result = new Result();
        StringBuilder buffer = new StringBuilder("select * from student ");
        if (!"".equals(str)) {
            buffer.append("where name like concat('%','").append(str).append("','%') ");
        }
        buffer.append(" order by createTime desc limit ? , ?");
        List<Map<String, Object>> maps = JDBCUtils.queryForList(String.valueOf(buffer), page, size);
        result.setCode(CodeEnum.SUCCESS.getStatusZh());
        result.setMsg(new ReturnMsg(MsgCode.MSG001).getMsg());
        result.setData(maps);
        return result;
    }

    @Override
    public Result insert(Student student) {
        Result result = new Result();
        String sql = "insert into student (id,name,age,number,address,createTime,remark) values (?,?,?,?,?,?,?)";
        List<Map<String, Object>> maps = JDBCUtils.queryForList( sql, student.getId(), student.getName(),
                student.getNumber(), student.getAddress(), student.getCreateTime(), student.getRemark());
        if (!maps.isEmpty()) {
            result.setCode(CodeEnum.SUCCESS.getStatusZh());
            result.setMsg(new ReturnMsg(MsgCode.MSG002).getMsg());
            result.setData(maps);
        } else {
            result.setCode(CodeEnum.FAULT.getStatusZh());
            result.setMsg(new ReturnMsg(MsgCode.ERR005).getMsg());
            result.setData(null);
        }
        return result;
    }

    @Override
    public Result update(Student student) {
        Result result = new Result();
        String sql = "update student set name = ?,age = ?,number =?,address =?,updateTime =? ,remark =? where id = ?";
        int i = JDBCUtils.update(sql, student.getName(), student.getNumber(), student.getAddress(), new Date(),
                student.getRemark(), student.getId());
        if (i == 1) {
            result.setCode(CodeEnum.SUCCESS.getStatusZh());
            result.setMsg(new ReturnMsg(MsgCode.MSG004).getMsg());
        } else {
            result.setCode(CodeEnum.FAULT.getStatusZh());
            result.setMsg(new ReturnMsg(MsgCode.ERR005).getMsg());
        }
        return result;
    }

    @Override
    public Result delete(String id) {
        Result result = new Result();
        String sql = "delete from student where id = ?";
        int i = JDBCUtils.update(sql, id);
        if (i == 1) {
            result.setCode(CodeEnum.SUCCESS.getStatusValue());
            result.setMsg(new ReturnMsg(MsgCode.MSG003).getMsg());
        } else {
            result.setCode(CodeEnum.FAULT.getStatusValue());
            result.setMsg(new ReturnMsg(MsgCode.ERR006).getMsg());
        }
        return result;
    }
}
