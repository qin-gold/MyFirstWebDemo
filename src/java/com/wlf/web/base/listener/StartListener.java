package com.wlf.web.base.listener;

import com.wlf.annotation.Listener;

import javax.servlet.ServletContextEvent;

/**
 * @author Qin
 * @createTime 2021/4/28 1:28
 */
@Listener
public class StartListener extends BaseListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("------项目启动成功------滋滋滋-----");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("------系统销毁------");
    }
}
