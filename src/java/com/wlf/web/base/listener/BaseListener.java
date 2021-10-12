package com.wlf.web.base.listener;

import cn.hutool.aop.aspects.SimpleAspect;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 基础Listener 继承了一个简易AOP
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/9 1:48
 */
public class BaseListener extends SimpleAspect implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
