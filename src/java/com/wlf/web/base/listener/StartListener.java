package com.wlf.web.base.listener;

import com.mysql.cj.util.LogUtils;
import com.wlf.annotation.Listener;
import com.wlf.utlis.PropertiesLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.util.Properties;

/**
 * @author Qin
 * @createTime 2021/4/28 1:28
 */
@Listener
public class StartListener extends BaseListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        Properties load = PropertiesLoadUtils.load("config.properties");
        context.setAttribute("config",load);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("------系统销毁------");
    }
}
