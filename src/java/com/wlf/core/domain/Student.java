package com.wlf.core.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 学生实体类
 *
 * @author QinShiJiao
 * @createTime 2021/4/26 23:06
 */
@Data
public class Student {
    private String id;
    private String name;
    private String age;
    private String number;
    private String address;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String remark;
}
