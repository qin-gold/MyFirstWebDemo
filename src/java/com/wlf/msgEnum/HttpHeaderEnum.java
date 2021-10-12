package com.wlf.msgEnum;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 13:29
 */
public enum HttpHeaderEnum {
    Cache_Control("Cache-Control"),
    Connection("Connection"),
    Accept("Accept"),
    Accept_Encoding("Accept-Encoding"),
    Host("Host"),
    Location("Location"),
    Content_Encoding("Content-Encoding"),
    Content_Language("Content-Language"),
    Content_Length("Content-Length"),
    Content_Location("Content-Location"),
    Content_Type("Content-Type"),
    Refresh("Refresh");

    // 状态值
    private final String statusValue;

    HttpHeaderEnum(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getStatusValue() {
        return statusValue;
    }
}
