package com.wlf.domain.dto;

import com.wlf.utlis.PropertiesLoadUtils;

/**
 * 返回消息公共模板
 * @author QinShijiao
 * @version 1.0
 * @createDate 2021/4/27 0:30
 */
public class ReturnMsg {
    private String code;
    private String msg;

    public ReturnMsg(String code) {
        this.code = code;
        String msg =code==null?"": PropertiesLoadUtils.load(code);
        this.msg = msg;
    }

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
}
