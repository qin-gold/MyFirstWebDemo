package com.wlf.domain.base;

import com.wlf.annotation.Column;
import com.wlf.annotation.Table;
import com.wlf.annotation.TablePk;
import com.wlf.msgEnum.DbType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 22:20
 */
@Data
@Table("b_log")
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    @TablePk
    @Column(value = "id",type = DbType.Varchar,length = 64,remark = "id")
    private String id;
    @Column(value = "title",type = DbType.Varchar,length = 64,remark = "操作")
    private String title;
    @Column(value = "userId",type = DbType.Varchar,length = 64,remark = "用户id")
    private String userId;
    @Column(value = "username",type = DbType.Varchar,length = 64,remark = "用户名称")
    private String username;
    @Column(value = "realIp",type = DbType.Varchar,length = 64,remark = "真实IP地址")
    private String realIp;
    @Column(value = "uri",type = DbType.Varchar,length = 64,remark = "访问url")
    private String uri;
    @Column(value = "createTime",type = DbType.DateTime,remark = "创建时间")
    private String createTime;
    @Column(value = "remark",type = DbType.Text,remark = "备注")
    private String remark;
}
