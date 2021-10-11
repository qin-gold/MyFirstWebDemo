package com.wlf.domain.base;

import com.wlf.annotation.Column;
import com.wlf.annotation.Table;
import com.wlf.annotation.TablePk;
import com.wlf.msgEnum.DbType;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 用户实体类
 *
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/11 13:53
 */
@Data
@Table("b_user")
public class User {
    @TablePk
    @Column(value = "id", type = DbType.Varchar, length = 64, remark = "用户id")
    private String id;
    @Column(value = "accountId", type = DbType.Varchar, length = 64, remark = "用户账户id")
    private String accountId;
    @Column(value = "name", type = DbType.Varchar, length = 64, remark = "用户姓名")
    private String name;
    @Column(value = "number", type = DbType.Varchar, length = 64, remark = "用户联系方式")
    private String number;
    @Column(value = "gender", type = DbType.Int, remark = "性别")
    private Integer gender;
    @Column(value = "address", type = DbType.Varchar, length = 255, remark = "地址")
    private String address;
    @Column(value = "createTime", type = DbType.DateTime, remark = "创建时间")
    private Timestamp createTime;
    @Column(value = "updateTime", type = DbType.DateTime, remark = "更新时间")
    private Timestamp updateTime;
    @Column(value = "remark", type = DbType.Text, remark = "备注")
    private String remark;
}
