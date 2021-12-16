package com.wlf.core.web.base.core;

import com.wlf.core.web.base.servlet.BaseServlet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

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
@Getter
@AllArgsConstructor
public class Model extends BaseServlet {
    private final HttpServletRequest request;
    private final HttpServletResponse response;

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
