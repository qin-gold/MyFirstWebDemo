package com.wlf.domain.base;

import com.wlf.annotation.Column;
import com.wlf.annotation.Table;
import com.wlf.msgEnum.DbType;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/11 17:17
 */
@Data
@Table("b_login_status")
public class LoginStatus {
    @Column(value = "id", type = DbType.Varchar, length = 64, remark = "id")
    private String id;
    @Column(value = "userId", type = DbType.Varchar, length = 64, remark = "用户id")
    private String userId;
    @Column(value = "username", type = DbType.Varchar, length = 64, remark = "用户名")
    private String username;
    @Column(value = "realIp", type = DbType.Varchar, length = 64, remark = "真实IP")
    private String realIp;
    @Column(value = "token", type = DbType.Varchar, length = 128, remark = "用户Token")
    private String token;
    @Column(value = "loginErrCount", type = DbType.Int, remark = "用户登录错误次数")
    private int loginErrCount;
    @Column(value = "loginTime", type = DbType.DateTime, remark = "用户上线时间")
    private Timestamp loginTime;
    @Column(value = "remark", type = DbType.Text, remark = "备注")
    private String remark;
}
