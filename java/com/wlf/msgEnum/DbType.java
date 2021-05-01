package com.wlf.msgEnum;

/**
 * 一个数据库类型枚举
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/4/30 4:24
 */
public enum DbType {
    Varchar("varchar"),
    Char("char"),
    Int("int"),
    DateTime("datatime"),
    Date("date"),
    Text("text");

    private  final  String value;

    DbType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
