package com.wlf.core.dao.base.impl;

import com.wlf.core.dao.base.LogDao;
import com.wlf.core.domain.base.Log;
import com.wlf.core.domain.dto.Result;
import com.wlf.core.domain.dto.ReturnMsg;
import com.wlf.core.enums.CodeEnum;
import com.wlf.core.enums.MsgCode;
import com.wlf.core.utlis.JDBCUtils;

import java.util.Date;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 22:32
 */
public class LogDaoImpl implements LogDao {

    @Override
    public Result findAllLog(String str) {
        Result result = new Result();

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
        Result result = new Result();
        String sql = "insert into b_log(id,title,username,userId,realIp,uri,createTime,remark) values (?,?,?,?,?,?,?,?,?,?)";
        int i = JDBCUtils.update(sql, log.getId(), log.getTitle(), log.getUsername(), log.getUserId(), log.getRealIp(), log.getUri(), new Date(), log.getRemark());
        if (i == 1) {
            result.setCode(CodeEnum.SUCCESS.getStatusValue());
            result.setMsg(new ReturnMsg(MsgCode.MSG002).getMsg());
        } else {
            result.setCode(CodeEnum.FAULT.getStatusValue());
            result.setMsg(new ReturnMsg(MsgCode.ERR004).getMsg());
        }
        return result;
    }
}
