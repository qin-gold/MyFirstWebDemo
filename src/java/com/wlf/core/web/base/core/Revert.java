package com.wlf.core.web.base.core;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 返回结果
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/21 13:36
 */
@Data
public abstract class Revert {
    protected String view;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public Revert setContext(HttpServletRequest request,HttpServletResponse response){
        this.request = request;
        this.response = response;
        return this;
    }
    public Revert setContext(HttpServletRequest request, HttpServletResponse response, String viewPath) {
        setContext(request,response);
        if (view != null && !view.startsWith("/"))
            view = viewPath + view;
        return this;
    }
}
