package com.wlf.domain.base.permission;

import com.wlf.annotation.Column;
import com.wlf.annotation.Table;
import com.wlf.annotation.TablePk;
import com.wlf.msgEnum.DbType;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/11 18:05
 */
@Data
@Table("b_authority_role")
public class Authority_role {
    @TablePk
    @Column(value = "id", type = DbType.Varchar, length = 64, remark = "id")
    private String id;
    @Column(value = "authorityId", type = DbType.Varchar, length = 64, remark = "权限Id")
    private String authorityId;
    @Column(value = "roleId", type = DbType.Varchar, length = 64, remark = "角色Id")
    private String roleId;
    @Column(value = "createTime", type = DbType.DateTime, remark = "创建时间")
    private Timestamp createTime;
    @Column(value = "updateTime", type = DbType.DateTime, remark = "更新时间")
    private Timestamp updateTime;
    @Column(value = "remark", type = DbType.Text, remark = "备注")
    private String remark;
}
