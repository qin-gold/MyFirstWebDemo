package com.wlf.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 登录实体类
 * @author QinShijiao
 * @version 1.0
 * @createDate 2021/4/26 23:08
 */
@Data
public class Account {
    private String id;
    private String username;
    private String password;
    private String userId;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String remark;
}
