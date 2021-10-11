package com.wlf.domain.base.permission;

import com.wlf.annotation.Column;
import com.wlf.annotation.Table;
import com.wlf.annotation.TablePk;
import com.wlf.msgEnum.DbType;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 菜单实体
 *
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/11 13:42
 */
@Data
@Table("b_menu")
public class Menu {
    @TablePk
    @Column(value = "id", type = DbType.Varchar, length = 64, remark = "id")
    private String id;
    @Column(value = "menuName", type = DbType.Varchar, length = 64, remark = "菜单名称")
    private String menuName;
    @Column(value = "url", type = DbType.Varchar, length = 128, remark = "菜单资源")
    private String url;
    @Column(value = "level", type = DbType.Int, remark = "深度")
    private Integer level;
    @Column(value = "parentId", type = DbType.Varchar, length = 64, remark = "父类Id")
    private String parentId;
    @Column(value = "createTime", type = DbType.DateTime, remark = "创建时间")
    private Timestamp createTime;
    @Column(value = "updateTime", type = DbType.DateTime, remark = "更新时间")
    private Timestamp updateTime;
    @Column(value = "remark", type = DbType.Text, remark = "备注")
    private String remark;
}
