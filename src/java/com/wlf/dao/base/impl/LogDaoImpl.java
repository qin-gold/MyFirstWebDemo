package com.wlf.dao.base.impl;

import com.wlf.dao.base.LogDao;
import com.wlf.domain.base.Log;
import com.wlf.domain.dto.Result;
import com.wlf.domain.dto.ReturnMsg;
import com.wlf.msgEnum.CodeEnum;
import com.wlf.msgEnum.MsgCode;
import com.wlf.utlis.JDBCUtils;

import java.sql.Connection;
import java.util.Date;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 22:32
 */
public class LogDaoImpl implements LogDao {
    private final Connection con = JDBCUtils.openConnection();

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
        int i = JDBCUtils.update(con, sql, log.getId(), log.getTitle(), log.getUsername(), log.getUserId(), log.getRealIp(), log.getUri(), new Date(), log.getRemark());
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
