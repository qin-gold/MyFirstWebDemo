package com.wlf.core.enums;

/**
 * Restful
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/21 12:27
 */
public enum HttpRequestEnum {
    POST("Post"),
    GET("Get"),
    PUT("Put"),
    DELETE("Delete"),
    HEAD("Head"),
    OPTIONS("Options");

    // 状态值
    private final String statusValue;

    HttpRequestEnum(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getStatusValue() {
        return statusValue;
    }
}
