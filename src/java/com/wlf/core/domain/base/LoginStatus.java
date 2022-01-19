package com.wlf.core.domain.base;

import cn.hutool.core.lang.UUID;
import com.wlf.core.annotation.Column;
import com.wlf.core.annotation.Table;
import com.wlf.core.annotation.TablePk;
import com.wlf.core.enums.DbType;
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
    @TablePk
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

    public LoginStatus(String userId, String username, String realIp, String token, int loginErrCount, String remark) {
        this.id = UUID.fastUUID().toString();
        this.userId = userId;
        this.username = username;
        this.realIp = realIp;
        this.token = token;
        this.loginErrCount = loginErrCount;
        this.remark = remark;
    }
}