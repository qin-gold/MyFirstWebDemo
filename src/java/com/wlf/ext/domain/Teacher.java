package com.wlf.ext.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 老师实体类
 *
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/4/26 23:07
 */
@Data
public class Teacher {
    private String id;
    private String name;
    private String age;
    private String number;
    private String address;
    private Subject subject;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String remark;
}
