package com.wlf.utlis;

/**
 * 这是一个数据封转类
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/4/26 23:56
*/
public class Result {
    private String code;
    private String msg;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
