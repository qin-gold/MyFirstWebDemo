package com.wlf.core.domain.base;

import com.wlf.core.annotation.Column;
import com.wlf.core.annotation.Table;
import com.wlf.core.annotation.TablePk;
import com.wlf.core.enums.DbType;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 账户实体类
 *
 * @author QinShijiao
 * @version 1.0
 * @createDate 2021/10/11 14:08
 */
@Data
@Table("b_account")
public class Account {
    @TablePk
    @Column(value = "id", type = DbType.Varchar, length = 64, remark = "id")
    private String id;
    @Column(value = "userId", type = DbType.Varchar, length = 64, remark = "用户id")
    private String userId;
    @Column(value = "accountType", type = DbType.Int, remark = "账户类型")
    private Integer accountType;
    @Column(value = "username", type = DbType.Varchar, length = 64, remark = "用户名")
    private String username;
    @Column(value = "password", type = DbType.Varchar, length = 128, remark = "密码")
    private String password;
    @Column(value = "status", type = DbType.Varchar, length = 64, remark = "账户状态")
    private Integer status;
    @Column(value = "createTime", type = DbType.DateTime, remark = "创建时间")
    private Timestamp createTime;
    @Column(value = "updateTime", type = DbType.DateTime, remark = "更新时间")
    private Timestamp updateTime;
    @Column(value = "remark", type = DbType.Text, remark = "备注")
    private String remark;
}
