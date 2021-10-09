package com.wlf.msgEnum;

/**
 * 一个数据库类型枚举
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/4/30 4:24
 */
public enum DbType {
    /**
     * varchar类型
     */
    Varchar("varchar"),
    /**
     * Char类型
     */
    Char("char"),
    /**
     * Int类型
     */
    Int("int"),
    /**
     * DateTime 类型
     */
    DateTime("datetime"),
    /**
     * Date类型
     */
    Date("date"),
    /**
     * 文档类型
     */
    Text("text");

    private  final  String value;

    DbType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
