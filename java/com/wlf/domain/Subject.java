package com.wlf.domain;

import java.sql.Timestamp;

/**
 * 老师科目实体类
 * @author QinShijiao
 * @version 1.0
 * @createDate 2021/4/26 23:09
 */
public class Subject {
    private String id;
    private String sub;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
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
