package com.wlf.core.dao.base.impl;

import com.wlf.core.dao.base.UserDao;
import com.wlf.core.domain.dto.Result;
import com.wlf.core.domain.dto.ReturnMsg;
import com.wlf.core.enums.CodeEnum;
import com.wlf.core.enums.MsgCode;
import com.wlf.core.utlis.JDBCUtils;

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
