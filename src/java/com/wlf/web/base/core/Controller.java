package com.wlf.web.base.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 控制Controller
 *
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/21 13:45
 */
public abstract class Controller {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String urlPara;

    void init(HttpServletRequest request,HttpServletResponse response,String urlPara){
        this.request = request;
        this.response = response;
        this.urlPara = urlPara;
    }

    public HttpSession getSession() {
        return request.getSession();
    }

    public Controller setSessionAttr(String name, Object value) {
        request.getSession().setAttribute(name, value);
        return this;
    }

    public Object getSessionAttr(String name) {
        return request.getSession().getAttribute(name);
    }

    public Controller setAttr(String name, Object value) {
        request.setAttribute(name, value);
        return this;
    }

    public String getAttrForStr(String name) {
        return (String) getAttr(name);
    }

    public int getAttrForInt(String name) {
        return (int) getAttr(name);
    }

    public String getUrlPara(String name) {
        return urlPara;
    }

    public Object getAttr(String name) {
        return request.getAttribute(name);
    }

}
