package com.wlf.msgEnum;

/**
 * 一个错误枚举类
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/4/26 22:55
*/
public enum CodeEnum {
    //正常状态
    SUCCESS("200","正常"),
    //错误状态
    FAULT("500","错误"),
    //异常状态
    NOTFOUND("404","异常");

    // 状态中文含义
    private final String statusZh;
    // 状态值
    private final String statusValue;

    CodeEnum(String statusZh, String statusValue) {
        this.statusZh = statusZh;
        this.statusValue = statusValue;
    }

    public String getStatusZh() {
        return statusZh;
    }

    public String getStatusValue() {
        return statusValue;
    }
}
