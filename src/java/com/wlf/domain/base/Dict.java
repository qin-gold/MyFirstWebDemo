package com.wlf.domain.base;

import cn.hutool.core.date.DateTime;
import com.wlf.annotation.Column;
import com.wlf.annotation.Table;
import com.wlf.annotation.TablePk;
import com.wlf.msgEnum.DbType;
import lombok.Data;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/11 17:50
 */
@Data
@Table("b_dict")
public class Dict {
    @TablePk
    @Column(value = "id", type = DbType.Varchar, length = 64, remark = "id")
    private String id;
    @Column(value = "name", type = DbType.Varchar, length = 64, remark = "字典名")
    private String name;
    @Column(value = "value", type = DbType.Varchar, length = 64, remark = "字典值")
    private String value;
    @Column(value = "level", type = DbType.Int, remark = "深度")
    private Integer level;
    @Column(value = "parentId", type = DbType.Varchar, length = 64, remark = "上一级Id")
    private String parentId;
    @Column(value = "creatTime", type = DbType.DateTime, remark = "创建时间")
    private DateTime creatTime;
    @Column(value = "updateTime", type = DbType.DateTime, remark = "更新时间")
    private DateTime updateTime;
    @Column(value = "remark", type = DbType.Text, remark = "备注")
    private DateTime remark;
}
