package com.wlf.core.web.base.listener;

import cn.hutool.aop.aspects.SimpleAspect;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 基础Listener 继承了一个简易AOP
 *
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/9 1:48
 */
public class BaseListener extends SimpleAspect implements ServletContextListener, HttpSessionListener, ServletRequestListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
