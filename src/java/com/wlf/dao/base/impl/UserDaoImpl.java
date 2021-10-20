package com.wlf.dao.base.impl;

import com.wlf.dao.base.UserDao;
import com.wlf.domain.dto.Result;
import com.wlf.domain.dto.ReturnMsg;
import com.wlf.msgEnum.CodeEnum;
import com.wlf.msgEnum.MsgCode;
import com.wlf.utlis.JDBCUtils;

import java.util.List;
import java.util.Map;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 18:51
 */
public class UserDaoImpl implements UserDao {
    @Override
    public Result findById(String id) {
        Result result = new Result();
        String sql = "select * from b_user where id = ?";
        List<Map<String, Object>> list = JDBCUtils.queryForList( sql, id);
        if (!list.isEmpty()) {
            result.setCode(CodeEnum.SUCCESS.getStatusValue());
            result.setData(list);
            result.setMsg(new ReturnMsg(MsgCode.MSG001).getMsg());
        } else {
            result.setCode(CodeEnum.FAULT.getStatusValue());
            result.setData(list);
            result.setMsg(new ReturnMsg(MsgCode.ERR001).getMsg());
        }
        return result;
    }
}
