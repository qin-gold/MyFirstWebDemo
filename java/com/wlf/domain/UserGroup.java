package com.wlf.domain;

import com.wlf.annotation.Column;
import com.wlf.annotation.Table;
import com.wlf.annotation.TablePk;
import com.wlf.msgEnum.DbType;

import java.sql.Timestamp;

/**
 * 组实体类
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/4/30 4:35
 */
@Table("userGroup")
public class UserGroup {
    @TablePk
    @Column(value = "id",type = DbType.Varchar,notNull = true)
    private String id;
    @Column(value = "name",type = DbType.Varchar)
    private String name;
    @Column(value = "createTime",type = DbType.DateTime)
    private Timestamp createTime;
    @Column(value = "updateTime",type = DbType.DateTime)
    private Timestamp updateTime;
    @Column(value = "remark",type = DbType.Varchar,length = 256)
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
