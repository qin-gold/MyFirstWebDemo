package com.wlf.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 老师科目实体类
 * @author QinShijiao
 * @version 1.0
 * @createDate 2021/4/26 23:09
 */
@Data
public class Subject {
    private String id;
    private String sub;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String remark;
}
