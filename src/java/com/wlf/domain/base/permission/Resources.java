package com.wlf.domain.base.permission;

import com.wlf.annotation.Column;
import com.wlf.annotation.Table;
import com.wlf.annotation.TablePk;
import com.wlf.msgEnum.DbType;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 权限资源实体
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/11 13:37
 */
@Data
@Table("b_resources")
public class Resources {
    @TablePk
    @Column(value = "id",type = DbType.Varchar,length = 64,remark = "id")
    private String id;
    @Column(value = "resName",type = DbType.Varchar,length = 64,remark = "权限名称")
    private String resName;
    @Column(value = "menuId",type = DbType.Varchar,length = 64,remark = "菜单Id")
    private String menuId;
    @Column(value = "dictValue",type = DbType.Varchar,length = 64,remark = "字典值")
    private String dictValue;
    @Column(value = "createTime",type = DbType.DateTime,remark = "创建时间")
    private Timestamp createTime;
    @Column(value = "updateTime",type = DbType.DateTime,remark = "更新时间")
    private Timestamp updateTime;
    @Column(value = "remark",type = DbType.Text,remark = "备注")
    private String remark;
}
