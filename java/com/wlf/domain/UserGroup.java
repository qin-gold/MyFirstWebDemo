package com.wlf.domain;

import com.wlf.annotation.Column;
import com.wlf.annotation.Table;
import com.wlf.annotation.TablePk;
import com.wlf.msgEnum.DbType;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 组实体类
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/4/30 4:35
 */
@Data
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
}
