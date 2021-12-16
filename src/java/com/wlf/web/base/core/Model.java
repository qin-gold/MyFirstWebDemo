package com.wlf.web.base.core;

import com.wlf.web.base.servlet.BaseServlet;

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
public class Model extends BaseServlet {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public Model(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public HttpSession getSession() {
        return request.getSession();
    }

    public Model setSessionAttr(String name, Object value) {
        request.getSession().setAttribute(name, value);
        return this;
    }

    public Object getSessionAttr(String name) {
        return request.getSession().getAttribute(name);
    }

    public Model setAttr(String name, Object value) {
        request.setAttribute(name, value);
        return this;
    }

    public String getAttrForStr(String name) {
        return (String) getAttr(name);
    }

    public int getAttrForInt(String name) {
        return (int) getAttr(name);
    }

    public Object getAttr(String name) {
        return request.getAttribute(name);
    }

    public String getStringPara(String name){return request.getParameter(name);}

}
