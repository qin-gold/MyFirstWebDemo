package com.wlf.web.base.filter.login;

import com.wlf.web.base.annotation.Controller;
import com.wlf.utlis.Scanner;
import com.wlf.web.base.filter.BaseFilter;
import com.wlf.web.base.main.QinStartConfig;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/12/14 19:22
 */
@WebFilter(value = "/*", initParams = {
        @WebInitParam(name = "MainName", value = "com.wlf.StartMain")
})
public class StartFilter extends BaseFilter {
    private QinStartConfig qinStartConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        createMain(filterConfig.getInitParameter("MainName"));
        qinStartConfig.afterStart();
        qinStartConfig.initOther(filterConfig.getServletContext());
    }

    @Override
    public void destroy() {
        qinStartConfig.beforeStop();
    }

    public void createMain(String className) {
        if ("".equals(className)) throw new RuntimeException("Please set the configuration class");
        try {
            Object instance = Class.forName(className).getDeclaredConstructor().newInstance();
            if (instance instanceof QinStartConfig) {
                qinStartConfig = (QinStartConfig) instance;
                return;
            }
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException("Can not create instance of class: " + className, e);
        }
    }

    public void init() {

    }
}
