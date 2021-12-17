package com.wlf.core.dao.base.impl;

import com.wlf.core.dao.base.AccountDao;
import com.wlf.core.domain.base.Account;
import com.wlf.core.domain.base.LoginStatus;
import com.wlf.core.domain.dto.Result;
import com.wlf.core.domain.dto.ReturnMsg;
import com.wlf.core.enums.CodeEnum;
import com.wlf.core.enums.MsgCode;
import com.wlf.core.utlis.JDBCUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 18:50
 */
public class AccountDaoImpl implements AccountDao {

    @Override
    public Result login(Account account) {
        Result result = new Result();
        String sql = "select id from b_account where username = ? and password = ?";
        List<Map<String, Object>> list = JDBCUtils.queryForList( sql, account.getUsername(), account.getPassword());
        if (!list.isEmpty()) {
            result.setCode(CodeEnum.SUCCESS.getStatusValue());
            result.setMsg(new ReturnMsg(MsgCode.MSG005).getMsg());
        } else {
            result.setCode(CodeEnum.FAULT.getStatusValue());
            result.setMsg(new ReturnMsg(MsgCode.ERR009).getMsg());
        }
        return result;
    }

    @Override
    public boolean checkAccount(String username) {
        String sql = "select id from b_account where username = ? ";
        int i = JDBCUtils.queryForInt(sql, username);
        return i == 1;
    }

    @Override
    public Result insert(Account account) {
        Result result = new Result();
        String sql = "insert into b_account(id,username,password,userId,createTime,remark) values (?,?,?,?,?,?)";
        int i = JDBCUtils.update(sql, account.getId(), account.getUsername(), account.getPassword(), account.getUserId(), new Date(), account.getRemark());
        if (i == 1) {
            result.setCode(CodeEnum.SUCCESS.getStatusValue());
            result.setMsg(new ReturnMsg(MsgCode.MSG002).getMsg());
        } else {
            result.setCode(CodeEnum.FAULT.getStatusValue());
            result.setMsg(new ReturnMsg(MsgCode.ERR007).getMsg());
        }
        return result;
    }

    @Override
    public Result update(Account account) {
        Result result = new Result();
        String sql = "update b_account set password = ?,updateTime = ?,remark =? where id = ?";
        int i = JDBCUtils.update( sql, account.getPassword(), new Date(),
                account.getRemark(), account.getId());
        if (i == 1) {
            result.setCode(CodeEnum.SUCCESS.getStatusValue());
            result.setMsg(new ReturnMsg(MsgCode.MSG004).getMsg());
        } else {
            result.setCode(CodeEnum.FAULT.getStatusValue());
            result.setMsg(new ReturnMsg(MsgCode.ERR005).getMsg());
        }
        return result;
    }

    @Override
    public Result delete(String id) {
        Result result = new Result();
        String sql = "delete from b_account where id = ?";
        int i = JDBCUtils.update( sql, id);
        if (i == 1) {
            result.setCode(CodeEnum.SUCCESS.getStatusValue());
            result.setMsg(new ReturnMsg(MsgCode.MSG003).getMsg());
        } else {
            result.setCode(CodeEnum.FAULT.getStatusValue());
            result.setMsg(new ReturnMsg(MsgCode.ERR006).getMsg());
        }
        return result;
    }

    @Override
    public Result isLogin(String username) {
        Result result = new Result();
        String sql = "select * from b_login_status where accountName =? and status = 2";
        int i = JDBCUtils.queryForInt( sql, username);
        if (i != 1) {
            result.setCode(CodeEnum.SUCCESS.getStatusValue());
            result.setMsg(new ReturnMsg(MsgCode.MSG011).getMsg());
        } else {
            result.setCode(CodeEnum.FAULT.getStatusValue());
            result.setMsg(new ReturnMsg(MsgCode.ERR011).getMsg());
        }
        return result;
    }

    @Override
    public void insertLog(LoginStatus loginStatus) {
        String sql = "insert into b_login_status(id,userId,username,realIp,token,loginErrCount,loginTime,remark)values(?,?,?,?,?,?,?,?)";
        JDBCUtils.update( sql, loginStatus.getId(), loginStatus.getUserId(),loginStatus.getUsername(),loginStatus.getRealIp(),loginStatus.getToken(),
               loginStatus.getLoginErrCount(),loginStatus.getLoginTime(),loginStatus.getRemark());
    }

    @Override
    public void delLoginLog(String id) {
        String sql = "delete from b_login_status where accountId = ?";
        JDBCUtils.update( sql,id);
    }

    @Override
    public void delAllLoginLog() {

    }
}
